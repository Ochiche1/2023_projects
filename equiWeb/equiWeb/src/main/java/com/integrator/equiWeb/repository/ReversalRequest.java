package com.integrator.equiWeb.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReversalRequest {
    private String sBranchNo;
    private String sClearingGL;
    private String sReceivableGL;
}
