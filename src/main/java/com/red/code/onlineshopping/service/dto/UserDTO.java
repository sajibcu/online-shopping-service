package com.red.code.onlineshopping.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.red.code.onlineshopping.database.entity.model.User;

@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @Email
    @Size(min = 5, max = 50)
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Size(min = 2, max = 25)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 25)
    private String lastName;

    private Integer userRole;

    private String recaptchaResponse;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this(user.getId(), user.getEmail(), null,
                user.getFirstName(), user.getLastName(),user.getUserRole(),"");
    }

}