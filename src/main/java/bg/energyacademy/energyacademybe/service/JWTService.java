package bg.energyacademy.energyacademybe.service;

import bg.energyacademy.energyacademybe.entity.User;

public interface JWTService {
    public String generateToken(User user);
}
