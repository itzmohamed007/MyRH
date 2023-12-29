package com.myrh.controllers;

import com.myrh.controllers.interfaces.IBasicController;
import com.myrh.dtos.noRelations.EmptySeekerOfferId;
import com.myrh.dtos.requests.ReqSeekerOffer;
import com.myrh.dtos.responses.ResSeekerOffer;
import com.myrh.services.SeekerOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applying")
@RequiredArgsConstructor
public class SeekerOfferController implements IBasicController<ReqSeekerOffer, ResSeekerOffer, EmptySeekerOfferId> {
    private final SeekerOfferService service;

    @Override
    @PostMapping("/get")
    public ResponseEntity<ResSeekerOffer> read(@RequestBody @Valid EmptySeekerOfferId emptySeekerOfferId) {
        return new ResponseEntity<>(this.service.read(emptySeekerOfferId), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ResSeekerOffer>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResSeekerOffer> create(@RequestBody @Valid ReqSeekerOffer reqSeekerOffer) {
        return new ResponseEntity<>(this.service.create(reqSeekerOffer), HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Valid EmptySeekerOfferId emptySeekerOfferId) {
        this.service.delete(emptySeekerOfferId);
        return new ResponseEntity<>(Map.of("message", "Seeker offer deleted successfully"), HttpStatus.OK);
    }
}
