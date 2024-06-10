package com.integrator.equiWeb.services.transactions.glToGL.impl;


import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GLToGLTransferRequest;
import com.integrator.equiWeb.services.transactions.glToGL.repository.database_results.GlToGlResponse;

public interface GLtoGLXferServices {
    GlToGlResponse glToGlXfer(GLToGLTransferRequest glToGLTransferRequest);
}
