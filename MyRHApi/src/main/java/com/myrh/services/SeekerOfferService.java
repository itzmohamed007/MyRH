package com.myrh.services;

import com.myrh.Embeddables.SeekerOfferId;
import com.myrh.dtos.noRelations.EmptySeekerOfferId;
import com.myrh.dtos.requests.ReqSeekerOffer;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.dtos.responses.ResSeekerOffer;
import com.myrh.exceptions.BadRequestException;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobOffer;
import com.myrh.models.JobSeeker;
import com.myrh.models.SeekerOffer;
import com.myrh.repositories.JobOfferRepository;
import com.myrh.repositories.JobSeekerRepository;
import com.myrh.repositories.SeekerOfferRepository;
import com.myrh.services.interfaces.ISeekerOfferService;
import com.myrh.utils.Utils;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeekerOfferService implements ISeekerOfferService {
    private final SeekerOfferRepository repository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResSeekerOffer read(EmptySeekerOfferId emptySeekerOfferId) {
        SeekerOfferId seekerOfferId = this.parseSeekerOfferId(emptySeekerOfferId);
        SeekerOffer seekerOffer = this.repository.findById(seekerOfferId)
                .orElseThrow(() -> new ResourceNotFoundException("No seeker offer was found"));
        return modelMapper.map(seekerOffer, ResSeekerOffer.class);
    }

    @Override
    public List<ResSeekerOffer> readAll() {
        List<SeekerOffer> seekerOffers = this.repository.findAll();
        if (seekerOffers.isEmpty()) throw new ResourceNotFoundException("No seeker offers were found");
        return seekerOffers.stream()
                .map(seekerOffer -> modelMapper.map(seekerOffer, ResSeekerOffer.class))
                .toList();
    }

    @Override
    public ResSeekerOffer create(ReqSeekerOffer reqSeekerOffer) {
        UUID jobSeekerUuid = Utils.parseStringToUuid(reqSeekerOffer.getJobSeeker());
        UUID jobOfferUuid = Utils.parseStringToUuid(reqSeekerOffer.getJobOffer());

        JobSeeker jobSeeker = this.jobSeekerRepository.findById(jobSeekerUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found with uuid " + reqSeekerOffer.getJobSeeker()));
        JobOffer jobOffer = this.jobOfferRepository.findById(jobOfferUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Job offer not found with uuid " + reqSeekerOffer.getJobOffer()));

        SeekerOfferId seekerOfferId = new SeekerOfferId(jobSeekerUuid, jobOfferUuid);

        if(this.repository.existsById(seekerOfferId))
            throw new BadRequestException("Job seeker with uuid " + jobSeekerUuid + " already had already applied to this job offer");

        SeekerOffer seekerOffer = new SeekerOffer(seekerOfferId, reqSeekerOffer.getLetter(), jobSeeker, jobOffer);
        SeekerOffer savedSeekerOffer = this.repository.save(seekerOffer);
        return modelMapper.map(savedSeekerOffer, ResSeekerOffer.class);
    }

    @Override
    public void delete(EmptySeekerOfferId emptySeekerOfferId) {
        SeekerOfferId seekerOfferId = parseSeekerOfferId(emptySeekerOfferId);
        this.read(emptySeekerOfferId);
        this.repository.deleteById(seekerOfferId);
    }

    private SeekerOfferId parseSeekerOfferId(EmptySeekerOfferId emptySeekerOfferId) {
        SeekerOfferId seekerOfferId = new SeekerOfferId();
        seekerOfferId.setOfferUuid(Utils.parseStringToUuid(emptySeekerOfferId.getOfferUuid()));
        seekerOfferId.setSeekerUuid(Utils.parseStringToUuid(emptySeekerOfferId.getSeekerUuid()));
        return seekerOfferId;
    }
}
