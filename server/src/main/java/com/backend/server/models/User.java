package com.backend.server.models;

import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Document(collection = "users")
public class User {
    // Nickname
    // Password
    // ID
    // email
    // Token
    @NotBlank(message = "Nickname is required")
    private String nickname;

    @NotBlank(message = "Password is required")
    private String password;

    @Id
    private long id;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private String token;

    private Instant created;

    private boolean enabled;

    public User(String nickname, String password, long id, String email, String token, Instant created, boolean enabled) {
        this.nickname = nickname;
        this.password = password;
        this.id = id;
        this.email = email;
        this.token = token;
        this.created = created;
        this.enabled = enabled;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
