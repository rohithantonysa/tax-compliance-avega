package com.avega.tax_compliance;

import com.avega.tax_compliance.entity.User;
import com.avega.tax_compliance.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaxComplianceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxComplianceApplication.class, args);
    }

    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User admin = userRepository.findByUsername("admin").orElse(new User());
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password123")); 
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            
            
            System.out.println("ADMIN PASSWORD FORCED TO 'password123'");
        };
    }
}