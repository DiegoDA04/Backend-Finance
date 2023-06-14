package pe.edu.group3.finance.backendfinance.security.resource;

import lombok.*;
import pe.edu.group3.finance.backendfinance.security.domain.model.entity.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String email;
    private List<Role> roles;
}
