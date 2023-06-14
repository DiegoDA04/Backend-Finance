package pe.edu.group3.finance.backendfinance.finance.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class PaymentScheduleResource {
    private Long n;
    private Long tea;
    private String tes;
    private String gracePeriod;
    private String initialBalance;
    private Long rate;
    private Long fee;
    private Long amortization;
    private Long endingBalance;
}
