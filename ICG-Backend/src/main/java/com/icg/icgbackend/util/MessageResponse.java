package com.icg.icgbackend.util;

import lombok.Data;
@Data
public class MessageResponse {
    private String message;
    public MessageResponse(String message) {
        this.message = message;
    }
}
