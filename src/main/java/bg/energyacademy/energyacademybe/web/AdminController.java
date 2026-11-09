package bg.energyacademy.energyacademybe.web;

import bg.energyacademy.energyacademybe.dto.AdminStatsDTO;
import bg.energyacademy.energyacademybe.entity.User;
import bg.energyacademy.energyacademybe.exception.ObjectNotFoundException;
import bg.energyacademy.energyacademybe.service.AdminService;
import bg.energyacademy.energyacademybe.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@EnableMethodSecurity
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;
    private final String superAdminEmail;

    public AdminController(
        UserService userService,
        AdminService adminService,
        @Value("${admin.email}") String superAdminEmail
    ) {
        this.userService = userService;
        this.adminService = adminService;
        this.superAdminEmail = superAdminEmail;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Principal principal) {
        String currentAdminMail = principal.getName();

        List<User> users = userService.getAllUsers().stream()
            .filter(u -> !u.getEmail().equals(currentAdminMail)
                && !u.getEmail().equals(superAdminEmail))
            .toList();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Something went wrong: " + ex.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedData) {
        User user = userService.updateUser(id, updatedData);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(adminService.getStats());
    }
}
