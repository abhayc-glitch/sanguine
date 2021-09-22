package com.server.backend.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

public class RegisterRequest {
  @JsonIgnore
  private String email;
  @JsonIgnore
  private String password;
  private String nickname;

}
