package com.myrh.services.interfaces;

import com.myrh.dtos.responses.ResJobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGlobalService<X, Y, Z> extends IBasicService<X, Y, Z> {
    ResJobOffer update(X x, Z z);
    Page<ResJobOffer> readAllPaginated(Pageable pageable);
}
