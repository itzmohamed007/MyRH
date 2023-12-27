package com.myrh.controllers;

import com.myrh.controllers.interfaces.IGlobalController;
import com.myrh.dtos.requests.ReqRecruiter;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.services.interfaces.IRecruiterService;
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
@RequestMapping("/api/recruiters")
@RequiredArgsConstructor
public class RecruiterController implements IGlobalController<ReqRecruiter, ResRecruiter, String> {
    private final IRecruiterService service;

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<ResRecruiter> read(@PathVariable String uuid) {
        return new ResponseEntity<>(this.service.read(uuid), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResRecruiter>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResRecruiter> create(@RequestBody @Valid ReqRecruiter reqRecruiter) {
        return new ResponseEntity<>(this.service.create(reqRecruiter), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String uuid) {
        this.service.delete(uuid);
        return new ResponseEntity<>(Map.of("message", "Recruiter deleted successfully"), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResRecruiter>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(this.service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<ResRecruiter> update(@RequestBody @Valid ReqRecruiter reqRecruiter, @PathVariable String uuid) {
        return new ResponseEntity<>(this.service.update(reqRecruiter, uuid), HttpStatus.OK);
    }
}
