package bg.energyacademy.energyacademybe.web;

import bg.energyacademy.energyacademybe.dto.AuthResponse;
import bg.energyacademy.energyacademybe.dto.LoginDTO;
import bg.energyacademy.energyacademybe.dto.UserDTO;
import bg.energyacademy.energyacademybe.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute UserDTO userDTO) {
        String token = userService.registerUser(userDTO);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
