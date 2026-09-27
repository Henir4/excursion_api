package com.viagens.rotaunica.config;
/**package com.viagens.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.viagens.rotaunica.model.User;
import com.viagens.rotaunica.model.UserRole;
import com.viagens.rotaunica.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration 
@Profile ("dev")
@RequiredArgsConstructor
@Slf4j 
public class DataSeeder {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  
  public CommandLineRunner seedUsers() {
    return args -> {
      if (userRepository.existsByEmail("admin@example.com")) {
        log.info("Admin user already exists.");
        return;
      }

      User admin = new User();
      admin.setEmail("admin@example.com");
      admin.setPassword(passwordEncoder.encode("admin123"));
      admin.setNickname("Admin");
      admin.setRole(UserRole.ADMIN);

      userRepository.save(admin);

      User guide = new User();
      guide.setEmail("guide@example.com");
      guide.setPassword(passwordEncoder.encode("guide123"));
      guide.setNickname("Guide");
      guide.setRole(UserRole.GUIDE);

      userRepository.save(guide);

      log.info("Seeded dev users: admin@example.com / admin123, guide@example.com / guide123");
    };
  }
}

**/