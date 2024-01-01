package com.myrh.services;

import com.myrh.dtos.requests.ReqRecruiter;
import com.myrh.dtos.responses.ResFile;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.enums.Status;
import com.myrh.exceptions.BadRequestException;
import com.myrh.exceptions.IllegalConstraintViolation;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.mappers.FileMapper;
import com.myrh.models.File;
import com.myrh.models.JobOffer;
import com.myrh.models.Recruiter;
import com.myrh.repositories.RecruiterRepository;
import com.myrh.services.interfaces.IFileService;
import com.myrh.services.interfaces.IRecruiterService;
import com.myrh.utils.Utils;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecruiterService implements IRecruiterService {
    private final ModelMapper modelMapper;
    private final RecruiterRepository repository;
    private final IFileService fileService;
    private final FileMapper fileMapper;

    @Override
    public ResRecruiter read(String stringUUid) {
        UUID uuid = Utils.parseStringToUuid(stringUUid);
        Recruiter recruiter = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No Recruiter was found with uuid "+ uuid));
        ResRecruiter resRecruiter = modelMapper.map(recruiter, ResRecruiter.class);
        resRecruiter.setImage(fileService.download(recruiter.getImage().getUuid()));

        return resRecruiter;
    }

    @Override
    public List<ResRecruiter> readAll() {
        List<Recruiter> recruiters = repository.findAll();
        if(recruiters.isEmpty()) throw new ResourceNotFoundException("No recruiters were found");
        List<ResRecruiter> resRecruiters = recruiters.stream()
                .map(recruiter -> modelMapper.map(recruiter, ResRecruiter.class))
                .toList();
        // fetching image for each recruiter
        resRecruiters.forEach(resRecruiter -> {
            resRecruiter.setImage(fileService.download(resRecruiter.getImage().getUuid()));
        });
        return resRecruiters;
    }

    @Override
    public ResRecruiter create(ReqRecruiter reqRecruiter) {
        try {
            Recruiter recruiter = modelMapper.map(reqRecruiter, Recruiter.class);
            ResFile resImage = fileService.download(Utils.parseStringToUuid(reqRecruiter.getImage()));
            System.out.println("downloaded image name: " + resImage.getName());
            recruiter.setImage(fileMapper.mapFileToUploadFormat(resImage));
            System.out.println("recruiter linked image uuid: " + recruiter.getImage().getUuid());
            Recruiter savedRecruiter = repository.save(recruiter);
            return modelMapper.map(savedRecruiter, ResRecruiter.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public void delete(String stringUUid) {
        UUID uuid = Utils.parseStringToUuid(stringUUid);
        this.checkRecruiterPresence(uuid);
        repository.deleteById(uuid);
    }

    @Override
    public ResRecruiter update(ReqRecruiter reqRecruiter, String recruiterUuid) {
        try {
            UUID parsedRecruiterUuid = Utils.parseStringToUuid(recruiterUuid);
            this.checkRecruiterPresence(parsedRecruiterUuid);
            reqRecruiter.setUuid(recruiterUuid); // insure recruiter modification, not creation
            Recruiter recruiter = modelMapper.map(reqRecruiter, Recruiter.class);
            Recruiter updatedRecruiter = repository.save(recruiter);
            return modelMapper.map(updatedRecruiter, ResRecruiter.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public Page<ResRecruiter> readAllPaginated(Pageable pageable) {
        Page<Recruiter> recruiters = repository.findAll(pageable);
        if(recruiters.isEmpty()) throw new ResourceNotFoundException("No recruiters were found");
        return recruiters.map(recruiter -> modelMapper.map(recruiter, ResRecruiter.class));
    }

    private void checkRecruiterPresence(UUID uuid) {
        this.repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No recruiter was found with uuid " + uuid));
    }
}
