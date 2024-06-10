package com.orbit.transaction.users.models;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class SecurityUser{
    private int id;
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be empty")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be empty")
    private String password;
    private String roles;

    public SecurityUser(String username, String password) {
        this.password = password;
        this.username = username;
    }
}

   // public User(String username, String password) {
   // }

