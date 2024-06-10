package com.orbit.transaction.inward.model;

import lombok.Data;
@Data
public class RefreshToken {
private String username;
private String tokenString;
private String expireAt;

}
