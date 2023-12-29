package com.myrh.controllers;

import com.myrh.controllers.interfaces.IBasicController;
import com.myrh.dtos.noRelations.EmptyApplyingId;
import com.myrh.dtos.requests.ReqSeekerOffer;
import com.myrh.dtos.responses.ResApplying;
import com.myrh.services.ApplyingService;
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
public class ApplyingController implements IBasicController<ReqSeekerOffer, ResApplying, EmptyApplyingId> {
    private final ApplyingService service;

    @Override
    @PostMapping("/get")
    public ResponseEntity<ResApplying> read(@RequestBody @Valid EmptyApplyingId emptySeekerOfferId) {
        return new ResponseEntity<>(this.service.read(emptySeekerOfferId), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ResApplying>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResApplying> create(@RequestBody @Valid ReqSeekerOffer reqSeekerOffer) {
        return new ResponseEntity<>(this.service.create(reqSeekerOffer), HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Valid EmptyApplyingId emptySeekerOfferId) {
        this.service.delete(emptySeekerOfferId);
        return new ResponseEntity<>(Map.of("message", "Seeker offer deleted successfully"), HttpStatus.OK);
    }
}
