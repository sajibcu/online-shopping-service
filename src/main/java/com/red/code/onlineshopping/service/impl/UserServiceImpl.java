package com.red.code.onlineshopping.service.impl;

import com.red.code.onlineshopping.common.exceptions.ActionNotPermittedException;
import com.red.code.onlineshopping.common.exceptions.BusinessRuleViolationException;
import com.red.code.onlineshopping.common.exceptions.EntityNotFoundException;
import com.red.code.onlineshopping.common.exceptions.UniqueIdTakenException;
import com.red.code.onlineshopping.common.utils.SecurityUtils;
import com.red.code.onlineshopping.database.entity.model.User;
import com.red.code.onlineshopping.repository.UserRepository;
import com.red.code.onlineshopping.service.CaptchaService;
import com.red.code.onlineshopping.service.UserService;
import com.red.code.onlineshopping.service.dto.UserDTO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private CaptchaService captchaService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,CaptchaService captchaService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.captchaService = captchaService;
    }

    //get all user info
    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    //get pageable user info
    @Override
    public Page<User> getUserByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserbyId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No user found with id: " + id));
    }

    @Override
    public User createNewUser(UserDTO userDTO) {

//        boolean captchaVerified = captchaService.verify(userDTO.getRecaptchaResponse());
//        log.debug("captchaVerified : "+captchaVerified);
//        if (!captchaVerified) {
//            throw new EntityNotFoundException("Captcha is not verified");
//        }

        verifyEmailUniqueness(userDTO.getEmail());
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        updateUserPassword(user, userDTO.getPassword(), true);
        user.setUserRole(2); // 2 for general user and 1 for admin user
        // TODO: set activated false, and activate after email is verified.
        user.setActivated(true);
        user.setLocked(false);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findOneById(id).ifPresent(user -> {
            if (user.getEmail().equals(SecurityUtils.getCurrentUserName())) {
                throw new ActionNotPermittedException("You cant delete yourself!");
            }
            userRepository.delete(user);
            log.debug("Deleted user: {}", user);
        });
    }

    @Override
    public User editUser(UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        if (user == null) {
            throw new EntityNotFoundException("No user found with id: " + userDTO.getId());
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserRole(userDTO.getUserRole());

        return userRepository.save(user);
    }


    @Override
    public void changeUserPassword(String email, String password) {
        userRepository.findOneByEmail(email).ifPresent(user -> {
            updateUserPassword(user, password, true);
            userRepository.save(user);
            log.debug("Change password for user: {}", user);
        });
    }

    @Override
    public void changePassword(Long id, String password) {
        User user = userRepository.findOneById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with id: " + id));
        updateUserPassword(user, password, true);
    }

    @Override
    public User getUserEmail(String email) {
        User user = userRepository.findOneByEmail(email).orElseThrow(() -> new EntityNotFoundException("No user found with email : " + email));
        return user;
    }

    private void verifyEmailUniqueness(String email) {
        userRepository.findOneByEmail(email).ifPresent(user -> {
            throw new UniqueIdTakenException("This email address is already taken. Choose another. Email: " + email);
        });
    }

    private void updateUserPassword(User user, String password, boolean checkStrength) {
        if (checkStrength)
            verifyPasswordStrength(user, password);
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
    }

    private void verifyPasswordStrength(User user, String password) {

        if (StringUtils.isEmpty(password)) {
            throw new BusinessRuleViolationException("Password cannot be empty!");
        }

        // 1. Web portal password must contain at least one numeric and one non-numeric characters, and must be at least 8 characters in length.
        if (password.length() < 8) {
            throw new BusinessRuleViolationException("Password length must be atleast 8.");
        }
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(password);
        if (!m.find()) {
            throw new BusinessRuleViolationException("Password must contain numeric character.");
        }

        if (StringUtils.isNumeric(password)) {
            throw new BusinessRuleViolationException("Password must contain non-numeric character.");
        }

        // 2. Passwords cannot be the repeated digits or characters, e.g. 11111, %%%%%%%%
        p = Pattern.compile("(.)\\1{2,}");
        m = p.matcher(password);
        if (m.find()) {
            throw new BusinessRuleViolationException("Password contains repeated character " + m.group(1));
        }

        // 3. Passwords cannot be the sequential digits or characters (in increasing or decreasing order), e.g. 12345,
        p = Pattern.compile("(012|123|234|345|456|567|678|789|987|876|765|654|543|432|321|210)");
        m = p.matcher(password);
        if (m.find()) {
            throw new BusinessRuleViolationException("Password contains sequence of digits " + m.group(1));
        }

        // 4. Passwords cannot be the same as the previous one or reverse order of it
        if (passwordEncoder.matches(password, user.getPassword()) || passwordEncoder.matches(StringUtils.reverse(password), user.getPassword())) {
            throw new BusinessRuleViolationException("Passwords cannot be the same as the previous one or reverse order of it.");
        }

        // 5. Web portal passwords cannot contain the unbroken string of user's first or last names
        if (StringUtils.lowerCase(password).contains(StringUtils.lowerCase(user.getFirstName()))
                || StringUtils.lowerCase(password).contains(StringUtils.lowerCase(user.getLastName()))) {
            throw new BusinessRuleViolationException("Password cannot contain the user's first or last name.");
        }
    }
}
