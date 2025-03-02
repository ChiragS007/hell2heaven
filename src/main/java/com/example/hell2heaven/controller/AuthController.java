package com.example.hell2heaven.controller;

import com.example.hell2heaven.entity.Earthling;
import com.example.hell2heaven.repository.EarthlingRepository;
import com.example.hell2heaven.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final EarthlingRepository earthlingRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, EarthlingRepository earthlingRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.earthlingRepository = earthlingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        Earthling user = earthlingRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
        if (earthlingRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String assignedRole = (request.role() != null) ? request.role().toUpperCase() : "EARTHLING";

        if (earthlingRepository.count() == 0) {
            assignedRole = "VACUUM_SUPREME";
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Earthling newUser = new Earthling(request.username(), encodedPassword, request.username() + "@gmail.com", assignedRole);

        earthlingRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully with role: " + assignedRole);
    }

}

record AuthRequest(String username, String password) {}
record SignUpRequest(String username, String password, String role) {}