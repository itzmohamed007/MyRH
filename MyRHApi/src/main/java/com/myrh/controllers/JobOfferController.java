package com.myrh.controllers;

import com.myrh.controllers.interfaces.IGlobalController;
import com.myrh.dtos.requests.ReqJobOffer;
import com.myrh.dtos.responses.ResJobOffer;
import com.myrh.services.JobOfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class JobOfferController implements IGlobalController<ReqJobOffer, ResJobOffer, String> {
    private final JobOfferService service;

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<ResJobOffer> read(@PathVariable String uuid) {
        return new ResponseEntity<>(service.read(uuid), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResJobOffer>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResJobOffer> create(@RequestBody @Valid ReqJobOffer reqJobOffer) {
        return new ResponseEntity<>(service.create(reqJobOffer), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String uuid) {
        service.delete(uuid);
        return new ResponseEntity<>(Map.of("message", "Job offer deleted successfully"), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResJobOffer>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<ResJobOffer> update(@RequestBody @Valid ReqJobOffer reqJobOffer, @PathVariable String uuid) {
        return new ResponseEntity<>(service.update(reqJobOffer, uuid), HttpStatus.OK);
    }
}
