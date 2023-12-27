package com.myrh.controllers;

import com.myrh.controllers.interfaces.IGlobalController;
import com.myrh.dtos.requests.ReqJobSeeker;
import com.myrh.dtos.responses.ResJobSeeker;
import com.myrh.services.interfaces.IJobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seekers")
@RequiredArgsConstructor
public class JobSeekerController implements IGlobalController<ReqJobSeeker, ResJobSeeker, String> {
    private final IJobSeekerService service;

    @Override
    public ResponseEntity<ResJobSeeker> read(String uuid) {
        return new ResponseEntity<>(this.service.read(uuid), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResJobSeeker>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResJobSeeker> create(ReqJobSeeker reqJobSeeker) {
        return new ResponseEntity<>(this.service.create(reqJobSeeker), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> delete(String uuid) {
        this.service.delete(uuid);
        return new ResponseEntity<>(Map.of("message", "Job seeker deleted successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ResJobSeeker>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(this.service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResJobSeeker> update(ReqJobSeeker reqJobSeeker, String uuid) {
        return new ResponseEntity<>(this.service.update(reqJobSeeker, uuid), HttpStatus.OK);
    }
}
