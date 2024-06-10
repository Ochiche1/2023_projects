package com.orbit.transaction.users.dto.response;

import com.orbit.transaction.users.models.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    public String token;
    public String responseMessage;
    public SecurityUser securityUser;

    public Response(String jwt, String username, String password, List<String> roles) {
    }
}
