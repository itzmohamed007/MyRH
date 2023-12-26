package com.myrh.services;

import com.myrh.dtos.requests.ReqRecruiter;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobOffer;
import com.myrh.models.Recruiter;
import com.myrh.repositories.RecruiterRepository;
import com.myrh.services.interfaces.IRecruiterService;
import com.myrh.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecruiterService implements IRecruiterService {
    private final ModelMapper modelMapper;
    private final RecruiterRepository repository;

    @Override
    public ResRecruiter read(String stringUUid) {
        UUID uuid = Utils.parseStringToUuid(stringUUid);
        Recruiter recruiter = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No Recruiter was found with uuid "+ uuid));
        return modelMapper.map(recruiter, ResRecruiter.class);
    }

    @Override
    public List<ResRecruiter> readAll() {
        return null;
    }

    @Override
    public ResRecruiter create(ReqRecruiter reqRecruiter) {
        return null;
    }

    @Override
    public void delete(String stringUUid) {

    }
}
