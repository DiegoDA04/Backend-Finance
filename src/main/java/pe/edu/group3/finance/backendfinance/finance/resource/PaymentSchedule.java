package pe.edu.group3.finance.backendfinance.finance.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSchedule {
    private Long n;
    private Double tea;
    private Double tes;
    private String gracePeriod;
    private Double initialBalance;
    private Double rate;
    private Double fee;
    private Double amortization;
    private Double endingBalance;
}
