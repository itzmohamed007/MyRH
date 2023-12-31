package com.myrh.services.interfaces;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IJobOfferService extends IGlobalService<ReqJobOffer, ResJobOffer, String> {

}
