package pe.edu.group3.finance.backendfinance.finance.resource;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferResource {

    @NotNull
    @NotBlank
    private String imageUrl;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Long salePriceInSoles;

    @NotNull
    private Long salePriceInDollars;
}
