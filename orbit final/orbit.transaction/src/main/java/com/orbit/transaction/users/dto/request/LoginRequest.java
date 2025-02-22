package com.orbit.transaction.users.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginRequest {
   @NotBlank
    private String username;
   @NotBlank
    private String password;
}
