package pe.edu.group3.finance.backendfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFinanceApplication.class, args);
    }

}
