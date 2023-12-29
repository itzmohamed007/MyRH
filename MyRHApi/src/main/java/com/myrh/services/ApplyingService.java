package com.myrh.services;

import com.myrh.Embeddables.ApplyingId;
import com.myrh.dtos.noRelations.EmptyApplyingId;
import com.myrh.dtos.requests.ReqSeekerOffer;
import com.myrh.dtos.responses.ResApplying;
import com.myrh.exceptions.BadRequestException;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobOffer;
import com.myrh.models.JobSeeker;
import com.myrh.models.Applying;
import com.myrh.repositories.JobOfferRepository;
import com.myrh.repositories.JobSeekerRepository;
import com.myrh.repositories.ApplyingRepository;
import com.myrh.services.interfaces.IApplyingService;
import com.myrh.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplyingService implements IApplyingService {
    private final ApplyingRepository repository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResApplying read(EmptyApplyingId emptySeekerOfferId) {
        ApplyingId seekerOfferId = this.parseSeekerOfferId(emptySeekerOfferId);
        Applying seekerOffer = this.repository.findById(seekerOfferId)
                .orElseThrow(() -> new ResourceNotFoundException("No seeker offer was found"));
        return modelMapper.map(seekerOffer, ResApplying.class);
    }

    @Override
    public List<ResApplying> readAll() {
        List<Applying> seekerOffers = this.repository.findAll();
        if (seekerOffers.isEmpty()) throw new ResourceNotFoundException("No seeker offers were found");
        return seekerOffers.stream()
                .map(seekerOffer -> modelMapper.map(seekerOffer, ResApplying.class))
                .toList();
    }

    @Override
    public ResApplying create(ReqSeekerOffer reqSeekerOffer) {
        UUID jobSeekerUuid = Utils.parseStringToUuid(reqSeekerOffer.getJobSeeker());
        UUID jobOfferUuid = Utils.parseStringToUuid(reqSeekerOffer.getJobOffer());

        JobSeeker jobSeeker = this.jobSeekerRepository.findById(jobSeekerUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found with uuid " + reqSeekerOffer.getJobSeeker()));
        JobOffer jobOffer = this.jobOfferRepository.findById(jobOfferUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Job offer not found with uuid " + reqSeekerOffer.getJobOffer()));

        ApplyingId seekerOfferId = new ApplyingId(jobSeekerUuid, jobOfferUuid);

        if(this.repository.existsById(seekerOfferId))
            throw new BadRequestException("Job seeker with uuid " + jobSeekerUuid + " already had already applied to this job offer");

        Applying seekerOffer = new Applying(seekerOfferId, reqSeekerOffer.getLetter(), jobSeeker, jobOffer);
        Applying savedSeekerOffer = this.repository.save(seekerOffer);
        return modelMapper.map(savedSeekerOffer, ResApplying.class);
    }

    @Override
    public void delete(EmptyApplyingId emptySeekerOfferId) {
        ApplyingId seekerOfferId = parseSeekerOfferId(emptySeekerOfferId);
        this.read(emptySeekerOfferId);
        this.repository.deleteById(seekerOfferId);
    }

    private ApplyingId parseSeekerOfferId(EmptyApplyingId emptySeekerOfferId) {
        ApplyingId seekerOfferId = new ApplyingId();
        seekerOfferId.setOfferUuid(Utils.parseStringToUuid(emptySeekerOfferId.getOfferUuid()));
        seekerOfferId.setSeekerUuid(Utils.parseStringToUuid(emptySeekerOfferId.getSeekerUuid()));
        return seekerOfferId;
    }
}
