package com.red.code.onlineshopping.web.rest.security;

import com.red.code.onlineshopping.common.exceptions.UserNotActivatedException;
import com.red.code.onlineshopping.database.entity.model.User;
import com.red.code.onlineshopping.repository.UserRepository;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    private final String adminEmail;

    public DomainUserDetailsService(UserRepository userRepository, @Value("${emerging.admin.email}")String adminEmail) {
        this.userRepository = userRepository;
        this.adminEmail = adminEmail;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository.findOneByEmail(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneByEmail(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        //FIXME: this role is hard coded. change it to role table
        List<String> roles;
        //if(lowercaseLogin.equalsIgnoreCase(adminEmail)){
        if(user.getUserRole()==1){ // role 1 is admin and other is normal user
            roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        }else {
            roles = Arrays.asList("ROLE_USER");
        }

        List<GrantedAuthority> grantedAuthorities = roles.stream()
            .map(authority -> new SimpleGrantedAuthority(authority))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            grantedAuthorities);
    }
}