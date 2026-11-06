package bg.energyacademy.energyacademybe.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    @PostMapping("/{id}/register")
    public ResponseEntity<?> registerForEvent(
        @PathVariable("id") Long eventId,
        Principal principal
    ) {
        // Save registration logic here...
        return ResponseEntity.ok(Map.of("message", "Successfully registered!"));
    }
}
