package com.myrh.services;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.models.JobOffer;
import com.myrh.repositories.JobOfferRepository;
import com.myrh.services.interfaces.IJobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobOfferService implements IJobOfferService {
    private final JobOfferRepository repository;

    @Override
    public ResJobOffer read(UUID uuid) {
//        return repository.findById(uuid).orElseThrow(() -> new );
    }

    @Override
    public List<ResJobOffer> readAll() {
        return null;
    }

    @Override
    public ResJobOffer create(ReqJobOffer jobOffer) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public ResJobOffer update(ReqJobOffer jobOffer, UUID uuid) {
        return null;
    }

    @Override
    public Page<ResJobOffer> readAllPaginated(Pageable pageable) {
        return null;
    }
}
