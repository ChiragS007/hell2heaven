package com.example.hell2heaven.config;

import com.example.hell2heaven.entity.Earthling;
import com.example.hell2heaven.repository.EarthlingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Optional;

@Configuration
public class AppConfig {

    private final EarthlingRepository earthlingRepository;

    public AppConfig(EarthlingRepository earthlingRepository) {
        this.earthlingRepository = earthlingRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Earthling> user = earthlingRepository.findByUsername(username);
            if (user.isPresent()) {
                return User.builder()
                        .username(user.get().getUsername())
                        .password(user.get().getPassword()) // Password is already encoded
                        .roles(user.get().getRole())
                        .build();
            }
            throw new RuntimeException("User not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
