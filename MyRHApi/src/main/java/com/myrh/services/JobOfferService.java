package com.myrh.services;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.exceptions.BadRequestException;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobOffer;
import com.myrh.repositories.JobOfferRepository;
import com.myrh.services.interfaces.IJobOfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferService implements IJobOfferService {
    private final JobOfferRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResJobOffer read(UUID uuid) {
        JobOffer jobOffer = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No Job were found with uuid "+ uuid));
        return modelMapper.map(jobOffer, ResJobOffer.class);
    }

    @Override
    public List<ResJobOffer> readAll() {
        List<JobOffer> jobOffers = repository.findAll();
        if(jobOffers.isEmpty()) throw new ResourceNotFoundException("No jobs were found");
        return jobOffers.stream()
                .map(jobOffer -> modelMapper.map(jobOffer, ResJobOffer.class))
                .toList();
    }

    @Override
    public ResJobOffer create(ReqJobOffer reqJobOffer) {
        JobOffer savedJobOffer = repository.save(modelMapper.map(reqJobOffer, JobOffer.class));
        return modelMapper.map(savedJobOffer, ResJobOffer.class);
    }

    @Override
    public void delete(UUID uuid) {
        this.read(uuid);
        repository.deleteById(uuid);
    }

    @Override
    public ResJobOffer update(ReqJobOffer reqJobOffer, UUID uuid) {
        try {
            this.read(uuid);
            reqJobOffer.setUuid(uuid); // insure job modification, not creation
            JobOffer updatedJobOffer = repository.save(modelMapper.map(reqJobOffer, JobOffer.class));
            return modelMapper.map(updatedJobOffer, ResJobOffer.class);
        } catch (IllegalArgumentException e) { throw new BadRequestException("please enter an available status"); }
    }

    @Override
    public Page<ResJobOffer> readAllPaginated(Pageable pageable) {
        Page<JobOffer> jobOffers = repository.findAll(pageable);
        if(jobOffers.isEmpty()) throw new ResourceNotFoundException("No jobs were found");
        return jobOffers.map(jobOffer -> modelMapper.map(jobOffer, ResJobOffer.class));
    }
}
