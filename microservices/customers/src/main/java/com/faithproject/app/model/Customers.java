package com.faithproject.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customers {
    private Integer id;
    private String fristName;
    private String lastName;
    private String email;
}
