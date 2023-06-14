package pe.edu.group3.finance.backendfinance.finance.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.group3.finance.backendfinance.finance.domain.model.entity.Offer;
import pe.edu.group3.finance.backendfinance.finance.resource.CreateOfferResource;
import pe.edu.group3.finance.backendfinance.finance.resource.OfferResource;
import pe.edu.group3.finance.backendfinance.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class OfferMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public OfferResource toResource(Offer model) {
        return mapper.map(model, OfferResource.class);
    }

    public Page<OfferResource> modelListPage(List<Offer> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, OfferResource.class), pageable, modelList.size());
    }

    public Offer toModel(CreateOfferResource resource) {
        return mapper.map(resource, Offer.class);
    }


}
