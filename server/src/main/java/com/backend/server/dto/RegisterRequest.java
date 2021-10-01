package com.backend.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RegisterRequest {
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private String nickname;

}