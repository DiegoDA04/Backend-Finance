package pe.edu.group3.finance.backendfinance.security.domain.service.communication;

import pe.edu.group3.finance.backendfinance.security.resource.UserResource;
import pe.edu.group3.finance.backendfinance.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}
