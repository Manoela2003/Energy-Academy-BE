package bg.energyacademy.energyacademybe.service;


import bg.energyacademy.energyacademybe.dto.LoginDTO;
import bg.energyacademy.energyacademybe.dto.UserDTO;
import bg.energyacademy.energyacademybe.entity.User;

public interface UserService {

    String registerUser(UserDTO userDTO);
    String loginUser(LoginDTO loginDTO);
}
