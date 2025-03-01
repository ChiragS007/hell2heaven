package com.example.hell2heaven.controller;

import com.example.hell2heaven.entity.Earthling;
import com.example.hell2heaven.repository.EarthlingRepository;
import com.example.hell2heaven.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;
    private final EarthlingRepository earthlingRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService, EarthlingRepository earthlingRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
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

        // ✅ Encode the password before saving
        String encodedPassword = passwordEncoder.encode(request.password());

        // ✅ Create new Earthling user (Correct Entity)
        Earthling newUser = new Earthling(request.username(), encodedPassword, request.username() + "@spacemail.com", request.role().toUpperCase());

        // ✅ Save to MongoDB
        earthlingRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

}

record AuthRequest(String username, String password) {}
record SignUpRequest(String username, String password, String role) {}