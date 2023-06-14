package pe.edu.group3.finance.backendfinance.finance.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import pe.edu.group3.finance.backendfinance.finance.domain.model.entity.Offer;
import pe.edu.group3.finance.backendfinance.finance.domain.persistence.OfferRepository;
import pe.edu.group3.finance.backendfinance.finance.domain.service.OfferService;
import pe.edu.group3.finance.backendfinance.finance.resource.PaymentSchedule;
import pe.edu.group3.finance.backendfinance.shared.exception.ResourceNotFoundException;
import pe.edu.group3.finance.backendfinance.shared.exception.ResourceValidationException;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String ENTITY = "Offer";

    private final OfferRepository offerRepository;

    private final Validator validator;

    public OfferServiceImpl(OfferRepository offerRepository, Validator validator) {
        this.offerRepository = offerRepository;
        this.validator = validator;
    }

    @Override
    public Offer getById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer create(Offer resource) {
        Set<ConstraintViolation<Offer>> violations = validator.validate(resource);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(offerRepository.existsByTitle(resource.getTitle()))
            throw new ResourceValidationException(ENTITY,"An Offer with the same title already exists.");

        return offerRepository.save(resource);
    }

    @Override
    public List<PaymentSchedule> createPaymentScheduleById(Long offerId, Double initialFee, String currency, Long years) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, offerId));

        Double bankLoansInDollars = offer.getSalePriceInDollars() - initialFee;
        Double bankLoansInSoles = offer.getSalePriceInSoles() - initialFee;
        Long nPeriods = years * 12;
        Double tea = 0.1399;
        Double tes = Math.pow(1.0 + tea, ((double) 30/360)) - 1;
        Double fee = bankLoansInSoles * ((tes * Math.pow( 1 + tes, nPeriods))/ (Math.pow(1 + tes, nPeriods) - 1));

        List<PaymentSchedule> paymentSchedules = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        for(int i = 1; i <= nPeriods; i++) {
            Double rate = tes * bankLoansInSoles;
            Double amortization = fee - rate;
            Double tempBankLoans = bankLoansInSoles;
            Double endingBalance = bankLoansInSoles - amortization;
            bankLoansInSoles = endingBalance;

            paymentSchedules.add(new PaymentSchedule()
                    .withN((long) i)
                    .withFee(Double.parseDouble(decimalFormat.format(fee)))
                    .withTea(tea)
                    .withTes(tes)
                    .withRate(Double.parseDouble(decimalFormat.format(rate)))
                    .withAmortization(Double.parseDouble(decimalFormat.format(amortization)))
                    .withEndingBalance(Double.parseDouble(decimalFormat.format(endingBalance)))
                    .withInitialBalance(Double.parseDouble(decimalFormat.format(tempBankLoans)))
                    .withGracePeriod("S")
            );
        }

        return paymentSchedules;
    }
}
