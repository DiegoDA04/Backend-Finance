package pe.edu.group3.finance.backendfinance.finance.resource;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class OfferResource {

    private Long id;
    private String imageUrl;
    private String title;
    private String description;
    private Long salePriceInSoles;

    private Long salePriceInDollars;
}
