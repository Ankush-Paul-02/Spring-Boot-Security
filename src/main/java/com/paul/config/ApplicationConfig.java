package com.paul.config;

import com.paul.repository.DeveloperRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {
    private final DeveloperRepository developerRepository;

    @Bean
    public UserDetailsService userDetailService() {
        return (username) -> {
            return (UserDetails)this.developerRepository.findByEmail(username).orElseThrow(() -> {
                return new UsernameNotFoundException("User not found!");
            });
        };
    }

    public ApplicationConfig(final DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }
}
