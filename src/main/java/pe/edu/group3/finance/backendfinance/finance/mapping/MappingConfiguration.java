package pe.edu.group3.finance.backendfinance.finance.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("financeMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public OfferMapper offerMapper(){ return new OfferMapper(); }
}
