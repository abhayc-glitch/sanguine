package com.backend.server.dto;

public class AuthResponse {

    private String response;

    public AuthResponse(String response) {
        this.response = response;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
}
