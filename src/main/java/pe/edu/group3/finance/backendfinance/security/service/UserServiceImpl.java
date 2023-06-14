package pe.edu.group3.finance.backendfinance.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.group3.finance.backendfinance.security.domain.model.entity.Role;
import pe.edu.group3.finance.backendfinance.security.domain.model.entity.User;
import pe.edu.group3.finance.backendfinance.security.domain.model.enumeration.Roles;
import pe.edu.group3.finance.backendfinance.security.domain.persistence.RoleRepository;
import pe.edu.group3.finance.backendfinance.security.domain.persistence.UserRepository;
import pe.edu.group3.finance.backendfinance.security.domain.service.UserService;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.LoginRequest;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.LoginResponse;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.RegisterRequest;
import pe.edu.group3.finance.backendfinance.security.domain.service.communication.RegisterResponse;
import pe.edu.group3.finance.backendfinance.security.resource.AuthenticateResource;
import pe.edu.group3.finance.backendfinance.security.resource.UserResource;
import pe.edu.group3.finance.backendfinance.shared.exception.ResourceNotFoundException;
import pe.edu.group3.finance.backendfinance.shared.mapping.EnhancedModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final EnhancedModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EnhancedModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User"));

            if(!request.getEmail().equals(user.getEmail()) || !user.getPassword().equals(request.getPassword())) {
                LoginResponse response = new LoginResponse("An error occurred while authenticating");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            List<String> roles = user.getRoles().stream()
                    .map(item -> item.getName().name())
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(user, AuthenticateResource.class);

            resource.setRoles(roles);
            LoginResponse response = new LoginResponse(resource);

            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            LoginResponse response = new LoginResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            RegisterResponse response = new RegisterResponse("Email is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if(rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found")));
            }

            User user = new User()
                    .withEmail(request.getEmail())
                    .withPassword(request.getPassword())
                    .withRoles(roles);

            userRepository.save(user);

            UserResource userResource = mapper.map(user, UserResource.class);
            RegisterResponse response = new RegisterResponse(userResource);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            RegisterResponse response = new RegisterResponse(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> logout() {
        return null;
    }
}
