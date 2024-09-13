package com.ranasoftcraft.org.ai.config;

public class AiException extends RuntimeException {

    public AiException(String response) {
        super(response);
    }
}