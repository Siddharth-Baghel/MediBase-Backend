package com.medibase.controller;

import com.medibase.dto.request.LoginRequest;
import com.medibase.dto.request.PharmacyRegistrationRequest;
import com.medibase.dto.request.RegisterRequest;
import com.medibase.dto.response.AuthResponse;
import com.medibase.entity.User;
import com.medibase.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){this.authService=authService;}

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){ return ResponseEntity.ok(authService.register(request)); }

    @PostMapping("/register-pharmacy")
    public ResponseEntity<AuthResponse> registerPharmacy(@Valid @RequestBody PharmacyRegistrationRequest request){
        return ResponseEntity.ok(authService.registerPharmacy(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){ return ResponseEntity.ok(authService.login(request)); }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(Authentication authentication){ return ResponseEntity.ok(authService.currentUser(authentication.getName())); }
}
