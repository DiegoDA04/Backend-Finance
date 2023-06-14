package pe.edu.group3.finance.backendfinance.security.domain.resource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String username;
    private String email;
}
