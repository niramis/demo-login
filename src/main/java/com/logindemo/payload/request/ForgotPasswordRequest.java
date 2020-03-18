package com.logindemo.payload.request;

import javax.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
