package pe.edu.group3.finance.backendfinance.security.persistence;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.group3.finance.backendfinance.security.domain.service.RoleService;

@Component
public class DatabaseSeedingConfig {
    private final RoleService roleService;

    public DatabaseSeedingConfig(RoleService roleService) {
        this.roleService = roleService;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        roleService.seed();
    }
}
