package pe.edu.group3.finance.backendfinance.finance.domain.service;

import pe.edu.group3.finance.backendfinance.finance.domain.model.entity.Offer;
import pe.edu.group3.finance.backendfinance.finance.resource.PaymentSchedule;

import java.util.List;

public interface OfferService {
    Offer getById(Long id);
    List<Offer> getAll();
    Offer create(Offer resource);
    List<PaymentSchedule> createPaymentScheduleById(Long offerId, Double initialFee, String currency, Long years);
}
