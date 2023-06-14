package pe.edu.group3.finance.backendfinance.finance.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.group3.finance.backendfinance.finance.domain.service.OfferService;
import pe.edu.group3.finance.backendfinance.finance.mapping.OfferMapper;
import pe.edu.group3.finance.backendfinance.finance.resource.CreateOfferResource;

@RestController
@RequestMapping("/api/v1/offers")
@Tag(name = "Offers", description = "Offers information")
public class OffersController {

    private final OfferService offerService;
    private final OfferMapper mapper;

    public OffersController(OfferService offerService, OfferMapper mapper) {
        this.offerService = offerService;
        this.mapper = mapper;
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getOfferById(@PathVariable Long userId) {
        return new ResponseEntity<>(mapper.toResource(offerService.getById(userId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOffer(@RequestBody CreateOfferResource resource) {
        return new ResponseEntity<>(offerService.create(mapper.toModel(resource)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOffers(Pageable pageable) {
        return new ResponseEntity<>(mapper.modelListPage(offerService.getAll(), pageable), HttpStatus.OK);
    }

    @GetMapping("{userId}/payment-schedule")
    public ResponseEntity<?> getPaymentScheduleByOfferId(
            @RequestParam(name = "initial-fee") Double initialFee,
            @RequestParam(name = "currency-type") String currency,
            @RequestParam(name = "years") Long years,
            @PathVariable Long userId) {
        return new ResponseEntity<>(offerService.createPaymentScheduleById(userId,initialFee,currency,years), HttpStatus.OK);
    }

}
