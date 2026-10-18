package bg.energyacademy.energyacademybe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDTO {
    @NotBlank(message = "Потребителското име е задължително")
    private String username;
    @NotBlank(message = "Паролата е задължителна")
    @Size(min = 6, message = "Паролата трябва да бъде поне 6 символа")
    private String password;
    @NotBlank(message = "Имейлът е задължителен")
    @Email(message = "Невалиден формат на имейл")
    private String email;
    @NotBlank(message = "Първото име е задължително")
    private String firstName;
    @NotBlank(message = "Фамилията е задължителна")
    private String lastName;
    private String phoneNumber;
    private MultipartFile profileImage;
}