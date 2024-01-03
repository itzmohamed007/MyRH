package com.myrh.controllers;

import com.myrh.controllers.interfaces.IGlobalController;
import com.myrh.dtos.requests.ReqJobSeeker;
import com.myrh.dtos.responses.ResJobSeeker;
import com.myrh.services.interfaces.IJobSeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seekers")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class JobSeekerController implements IGlobalController<ReqJobSeeker, ResJobSeeker, String> {
    private final IJobSeekerService service;

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<ResJobSeeker> read(@PathVariable String uuid) {
        return new ResponseEntity<>(this.service.read(uuid), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResJobSeeker>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResJobSeeker> create(@RequestBody @Valid ReqJobSeeker reqJobSeeker) {
        return new ResponseEntity<>(this.service.create(reqJobSeeker), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String uuid) {
        this.service.delete(uuid);
        return new ResponseEntity<>(Map.of("message", "Job seeker deleted successfully"), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResJobSeeker>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(this.service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<ResJobSeeker> update(@RequestBody @Valid ReqJobSeeker reqJobSeeker, @PathVariable String uuid) {
        return new ResponseEntity<>(this.service.update(reqJobSeeker, uuid), HttpStatus.OK);
    }
}
