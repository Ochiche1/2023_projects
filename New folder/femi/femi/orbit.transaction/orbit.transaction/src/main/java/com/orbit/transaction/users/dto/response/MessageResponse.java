package com.orbit.transaction.users.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
