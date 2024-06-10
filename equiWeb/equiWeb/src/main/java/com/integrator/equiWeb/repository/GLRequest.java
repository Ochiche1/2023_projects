package com.integrator.equiWeb.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GLRequest {
    private Long service_id;
    private String gl_acct_non_proprietary;
}
