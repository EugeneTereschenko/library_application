package com.library.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@ToString
@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    private String username;
    private String password;
    private String isActive;
}
