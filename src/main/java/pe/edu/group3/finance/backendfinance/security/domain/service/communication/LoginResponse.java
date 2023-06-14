package pe.edu.group3.finance.backendfinance.security.domain.service.communication;

import pe.edu.group3.finance.backendfinance.security.resource.AuthenticateResource;
import pe.edu.group3.finance.backendfinance.security.resource.UserResource;
import pe.edu.group3.finance.backendfinance.shared.domain.service.communication.BaseResponse;

public class LoginResponse extends BaseResponse<AuthenticateResource> {
    public LoginResponse(String message) {
        super(message);
    }

    public LoginResponse(AuthenticateResource resource) {
        super(resource);
    }
}
