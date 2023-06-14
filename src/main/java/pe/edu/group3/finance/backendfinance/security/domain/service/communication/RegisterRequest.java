package pe.edu.group3.finance.backendfinance.security.domain.service.communication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;
    @JsonIgnore
    private Set<String> roles;
}
