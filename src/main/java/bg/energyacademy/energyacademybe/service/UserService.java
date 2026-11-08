package bg.energyacademy.energyacademybe.service;


import bg.energyacademy.energyacademybe.dto.LoginDTO;
import bg.energyacademy.energyacademybe.dto.UserDTO;
import bg.energyacademy.energyacademybe.entity.User;

import java.util.List;

public interface UserService {

    String registerUser(UserDTO userDTO);
    String loginUser(LoginDTO loginDTO);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User updateUser(Long id, User user);
}
