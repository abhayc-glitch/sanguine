package com.server.backend.models;


// CHANGE THISSSSSSSSSSssssssssšsssss
public class Quote {
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

  public Quote(String nickname, String password, String id, String email, String token) {
    this.nickname = nickname;
    this.password = password;
    this.email = email;
    this.token = token;
  }

  // Json Object for communicating serializing and transmitting structured data
  // over a network connection
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
