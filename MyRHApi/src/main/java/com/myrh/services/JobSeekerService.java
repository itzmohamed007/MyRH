package com.myrh.services;

import com.myrh.dtos.requests.ReqJobSeeker;
import com.myrh.dtos.responses.ResJobSeeker;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.exceptions.IllegalConstraintViolation;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.JobSeeker;
import com.myrh.models.Recruiter;
import com.myrh.repositories.JobSeekerRepository;
import com.myrh.services.interfaces.IJobSeekerService;
import com.myrh.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobSeekerService implements IJobSeekerService {
    private final JobSeekerRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResJobSeeker read(String stringUUid) {
        UUID uuid = Utils.parseStringToUuid(stringUUid);
        JobSeeker jobSeeker = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No Job seeker was found with uuid "+ uuid));
        return modelMapper.map(jobSeeker, ResJobSeeker.class);
    }

    @Override
    public List<ResJobSeeker> readAll() {
        List<JobSeeker> jobSeekers = repository.findAll();
        if(jobSeekers.isEmpty()) throw new ResourceNotFoundException("No job seekers were found");
        return jobSeekers.stream()
                .map(jobSeeker -> modelMapper.map(jobSeeker, ResJobSeeker.class))
                .toList();
    }

    @Override
    public ResJobSeeker create(ReqJobSeeker reqJobSeeker) {
        try {
            String identifier = UUID.randomUUID().toString();
            JobSeeker jobSeeker = modelMapper.map(reqJobSeeker, JobSeeker.class);
            jobSeeker.setIdentifier(identifier);
            JobSeeker savedJobSeeker = repository.save(jobSeeker);
            return modelMapper.map(savedJobSeeker, ResJobSeeker.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public void delete(String stringUUid) {
        UUID uuid = Utils.parseStringToUuid(stringUUid);
        this.checkJobSeekerPresence(uuid);
        repository.deleteById(uuid);
    }

    @Override
    public ResJobSeeker update(ReqJobSeeker reqJobSeeker, String JobSeekerUuid) {
        try {
            UUID parsedJobSeekerUuid = Utils.parseStringToUuid(JobSeekerUuid);
            this.checkJobSeekerPresence(parsedJobSeekerUuid);
            reqJobSeeker.setIdentifier(UUID.randomUUID().toString()); // setting random identifier for the moment
            JobSeeker jobSeeker = modelMapper.map(reqJobSeeker, JobSeeker.class);
            jobSeeker.setUuid(parsedJobSeekerUuid);
            JobSeeker updatedJobSeeker = repository.save(jobSeeker);
            return modelMapper.map(updatedJobSeeker, ResJobSeeker.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public Page<ResJobSeeker> readAllPaginated(Pageable pageable) {
        Page<JobSeeker> jobSeekers = repository.findAll(pageable);
        if(jobSeekers.isEmpty()) throw new ResourceNotFoundException("No job seekers were found");
        return jobSeekers.map(jobSeeker -> modelMapper.map(jobSeeker, ResJobSeeker.class));
    }

    private void checkJobSeekerPresence(UUID uuid) {
        this.repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No job seeker was found with uuid " + uuid));
    }
}
