package pe.edu.group3.finance.backendfinance.security.domain.service;

import org.springframework.http.ResponseEntity;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.LoginRequest;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.RegisterRequest;

public interface UserService {
    ResponseEntity<?> authenticate(LoginRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> logout();
}
