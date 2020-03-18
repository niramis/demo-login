package com.logindemo.payload.request;

import javax.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String token;

    public ResetPasswordRequest(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotBlank String token) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
