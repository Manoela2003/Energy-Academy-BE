package bg.energyacademy.energyacademybe.service.impl;

import bg.energyacademy.energyacademybe.dto.LoginDTO;
import bg.energyacademy.energyacademybe.dto.UserDTO;
import bg.energyacademy.energyacademybe.entity.User;
import bg.energyacademy.energyacademybe.entity.UserRole;
import bg.energyacademy.energyacademybe.exception.DuplicateEmailException;
import bg.energyacademy.energyacademybe.exception.DuplicateUsernameException;
import bg.energyacademy.energyacademybe.repository.UserRepository;
import bg.energyacademy.energyacademybe.service.JWTService;
import bg.energyacademy.energyacademybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String registerUser(UserDTO userDTO) {
        if (userDTO.getPassword().length() < 6) {
            throw new DuplicateUsernameException(userDTO.getUsername());
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateEmailException(userDTO.getEmail());
        }

        String imagePath = null;

        if (userDTO.getProfileImage() != null && !userDTO.getProfileImage().isEmpty()) {
            try {
                String uploadDir = "uploads/profiles/";
                java.io.File uploadFolder = new java.io.File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                String originalFilename = userDTO.getProfileImage().getOriginalFilename();
                String fileExtension = "";

                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                String safeUsername = userDTO.getUsername().replaceAll("[^a-zA-Z0-9_]", "_");

                String fileName = safeUsername + fileExtension;

                Path filePath = Paths.get("uploads/profiles/" + fileName);
                Files.copy(userDTO.getProfileImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                imagePath = "/uploads/profiles/" + fileName;
            } catch (Exception e) {
                throw new RuntimeException("Грешка при качване на снимката: " + e.getMessage());
            }
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPhotoUrl(imagePath);
        user.setRole(UserRole.ROLE_USER);

        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    @Override
    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getIdentifier())
                .or(() -> userRepository.findByUsername(loginDTO.getIdentifier()))
                .orElseThrow(() -> new RuntimeException("Невалиден имейл/потребителско име"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Грешна парола");
        }

        return jwtService.generateToken(user);
    }
}
