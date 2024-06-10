package com.orbit.transaction.users.models;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {

  private int id;

  private SecurityUser securityUser;

  private String token;

  private Date expiryDate;

}
