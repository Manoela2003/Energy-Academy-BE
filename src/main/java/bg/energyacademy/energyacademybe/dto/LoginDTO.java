package bg.energyacademy.energyacademybe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Имейлът или потребителското име са задължителни")
    private String identifier;

    @NotBlank(message = "Паролата е задължителна")
    private String password;
}