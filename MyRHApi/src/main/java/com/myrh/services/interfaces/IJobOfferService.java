package com.myrh.services.interfaces;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IJobOfferService extends GlobalService<ReqJobOffer, ResJobOffer, UUID> {
    ResJobOffer update(ReqJobOffer jobOffer, UUID uuid);
    Page<ResJobOffer> readAllPaginated(Pageable pageable);
}
