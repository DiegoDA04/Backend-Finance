package pe.edu.group3.finance.backendfinance.security.domain.service.communication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
