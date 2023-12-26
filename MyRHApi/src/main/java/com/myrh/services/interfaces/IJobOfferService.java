package com.myrh.services.interfaces;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IJobOfferService extends GlobalService<ReqJobOffer, ResJobOffer, String> {
    ResJobOffer update(ReqJobOffer jobOffer, String uuid);
    Page<ResJobOffer> readAllPaginated(Pageable pageable);
}
