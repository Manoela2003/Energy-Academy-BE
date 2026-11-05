package bg.energyacademy.energyacademybe.service;

import bg.energyacademy.energyacademybe.entity.User;

public interface JWTService {
    String generateToken(User user);
    String extractUsername(String token);
    boolean validateToken(String token);
}
