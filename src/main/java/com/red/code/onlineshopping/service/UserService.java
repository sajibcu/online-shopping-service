package com.red.code.onlineshopping.service;

import com.red.code.onlineshopping.database.entity.model.User;
import com.red.code.onlineshopping.service.dto.UserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    //get all user info
    List<User> getUser();

    Page<User> getUserByPage(Pageable pageable);

    User getUserbyId(Long id);

    User createNewUser(UserDTO u);

    void deleteUser(Long id);

    User editUser(UserDTO userDTO);

    void changeUserPassword(String email, String password);

    void changePassword(Long id, String password);

    User getUserEmail(String email);
}
