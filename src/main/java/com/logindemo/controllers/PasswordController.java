package com.logindemo.controllers;

import com.logindemo.models.User;
import com.logindemo.payload.request.ForgotPasswordRequest;
import com.logindemo.payload.request.ResetPasswordRequest;
import com.logindemo.payload.response.ForgotPasswordResponse;
import com.logindemo.payload.response.MessageResponse;
import com.logindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private UserRepository user_repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {

        Optional<User> user = user_repository.findByUsername(resetPasswordRequest.getUsername());

        if (user.isPresent()) {
            if (user.get().getResetToken().equals(resetPasswordRequest.getToken())) {
                User u = user.get();
                u.clearResetToken();
                u.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
                user_repository.save(u);

                String message = "Correct password reset";
                return ResponseEntity.ok(new MessageResponse(message));
            } else {
                String message = "Incorrect password reset - bad token";
                return ResponseEntity.ok(new MessageResponse(message));
            }
        } else {
            String message = "Incorrect password reset";
            return ResponseEntity.ok(new MessageResponse(message));
        }
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        Optional<User> user = user_repository.findByUsername(forgotPasswordRequest.getUsername());

        if (user.isPresent()) {
            String resetToken = UUID.randomUUID().toString();
            User u = user.get();
            u.setResetToken(resetToken);
            user_repository.save(u);
            return ResponseEntity.ok(new ForgotPasswordResponse(resetToken));
        } else {
            return ResponseEntity.badRequest()
                    .body("Invalid forgot password request");
        }
    }
}
