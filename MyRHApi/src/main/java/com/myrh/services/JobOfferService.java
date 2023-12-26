package com.myrh.services;

import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.enums.Status;
import com.myrh.exceptions.BadRequestException;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobOffer;
import com.myrh.repositories.JobOfferRepository;
import com.myrh.repositories.RecruiterRepository;
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
    private final RecruiterRepository recruiterRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResJobOffer read(String stringUuid) {
        UUID uuid = this.parseStringToUuid(stringUuid);
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
        this.checkRecruiterPresence(this.parseStringToUuid(reqJobOffer.getRecruiter()));
        reqJobOffer.setStatus("pending");
        JobOffer savedJobOffer = repository.save(modelMapper.map(reqJobOffer, JobOffer.class));
        return modelMapper.map(savedJobOffer, ResJobOffer.class);
    }

    @Override
    public void delete(String jobOfferUuid) {
        UUID uuid = this.parseStringToUuid(jobOfferUuid);
        this.checkJobOfferPresence(uuid);
        repository.deleteById(uuid);
    }

    @Override
    public ResJobOffer update(ReqJobOffer reqJobOffer, String jobOfferUuid) {
        try {
            UUID parsedJobOfferUuid = this.parseStringToUuid(jobOfferUuid);
            UUID parsedRecruiterUuid = this.parseStringToUuid(reqJobOffer.getRecruiter());
            this.checkRecruiterPresence(parsedRecruiterUuid);
            this.checkJobOfferPresence(parsedJobOfferUuid);
            reqJobOffer.setUuid(jobOfferUuid); // insure job modification, not creation
            JobOffer jobOffer = modelMapper.map(reqJobOffer, JobOffer.class);
            jobOffer.setStatus(Status.valueOf(reqJobOffer.getStatus()));
            JobOffer updatedJobOffer = repository.save(jobOffer);
            return modelMapper.map(updatedJobOffer, ResJobOffer.class);
        } catch (IllegalArgumentException e) { throw new BadRequestException("please enter an available status ['pending', 'accepted', 'refused']"); }
    }

    @Override
    public Page<ResJobOffer> readAllPaginated(Pageable pageable) {
        Page<JobOffer> jobOffers = repository.findAll(pageable);
        if(jobOffers.isEmpty()) throw new ResourceNotFoundException("No jobs were found");
        return jobOffers.map(jobOffer -> modelMapper.map(jobOffer, ResJobOffer.class));
    }

    private void checkRecruiterPresence(UUID recruiterUuid) {
        recruiterRepository.findById(recruiterUuid)
                .orElseThrow(() -> new ResourceNotFoundException("no recruiter was found with uuid " + recruiterUuid));
    }

    private void checkJobOfferPresence(UUID jobOfferUuid) {
        this.repository.findById(jobOfferUuid).orElseThrow(() -> new ResourceNotFoundException("No job offer was found with uuid " + jobOfferUuid));
    }

    private UUID parseStringToUuid(String stringUuid) {
        try { return UUID.fromString(stringUuid); }
        catch (IllegalArgumentException e) { throw new BadRequestException("please enter a valid UUID format"); }
    }
}
