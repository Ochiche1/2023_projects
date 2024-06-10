package com.orbit.transaction.users.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
  //  @NotBlank
    private String username;
   // @NotBlank
    private String password;
    private String roles;
}
