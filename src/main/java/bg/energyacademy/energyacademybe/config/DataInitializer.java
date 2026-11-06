package bg.energyacademy.energyacademybe.config;

import bg.energyacademy.energyacademybe.entity.User;
import bg.energyacademy.energyacademybe.entity.UserRole;
import bg.energyacademy.energyacademybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Value("${admin.email:admin@energyacademy.com}")
    private String adminEmail;

    @Value("${admin.password:DefaultLocalDevPassword123!}")
    private String adminPassword;

    @Bean
    CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setFirstName("Admin");
                admin.setLastName("Admin");
                admin.setRole(UserRole.ROLE_ADMIN);

                userRepository.save(admin);
                System.out.println(">>> Default Admin user created successfully via environment config!");
            }
        };
    }
}