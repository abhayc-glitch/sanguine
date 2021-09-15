package com.server.backend.models;

import io.vertx.core.json.JsonObject;

public class User {
  // Nickname
  // Password
  // ID
  // email
  // Token
  String nickname;

  String password;

  String id;

  String email;

  String token;

  public User(String nickname, String password, String id, String email, String token) {
    this.nickname = nickname;
    this.password = password;
    this.email = email;
    this.token = token;
  }

  // Json Object for communicating serializing and transmitting structured data
  // over a network connection.
  public JsonObject toSanguineJson() {
    JsonObject retVal = new JsonObject();
    JsonObject user = new JsonObject().put("nickname", this.nickname).put("email", this.email).put("token", this.token)
        .put("id", this.id);
    retVal.put("user", user);
    return retVal;
  }
  // Getters and Setters

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    nickname = this.nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    password = this.password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    email = this.email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    token = this.token;
  }



}
