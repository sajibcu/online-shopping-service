package com.red.code.onlineshopping.web.rest;

import com.red.code.onlineshopping.common.utils.SecurityUtils;
import com.red.code.onlineshopping.service.UserService;
import com.red.code.onlineshopping.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        log.debug("hello");
        return userService.getUser()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/users/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDTO> getUserPageable(Pageable pageable) {
        log.debug("page; {}", pageable);
        return userService.getUserByPage(pageable).map(UserDTO::new);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserbyId(@PathVariable("id") Long id) {
        return new UserDTO(userService.getUserbyId(id));
    }

    @GetMapping(value = "/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getCurrentUserDetails() {
        return new UserDTO(userService.getUserEmail(SecurityUtils.getCurrentUserName()));
    }

    @PostMapping(value = "/users/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO addNewUser(@RequestBody @Validated UserDTO userDTO) {
        return new UserDTO(userService.createNewUser(userDTO));
    }

    @PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO editUser(@RequestBody @Validated UserDTO userDTO) {
        return new UserDTO(userService.editUser(userDTO));
    }


    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
