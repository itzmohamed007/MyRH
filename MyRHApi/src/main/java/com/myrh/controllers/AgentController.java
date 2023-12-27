package com.myrh.controllers;

import com.myrh.controllers.interfaces.IGlobalController;
import com.myrh.dtos.requests.ReqAgent;
import com.myrh.dtos.responses.ResAgent;
import com.myrh.services.interfaces.IAgentService;
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
@RequestMapping("/agents")
@RequiredArgsConstructor
public class AgentController implements IGlobalController<ReqAgent, ResAgent, String> {
    private final IAgentService service;

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<ResAgent> read(@PathVariable String uuid) {
        return new ResponseEntity<>(this.service.read(uuid), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<ResAgent>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResAgent> create(@RequestBody @Valid ReqAgent reqAgent) {
        return new ResponseEntity<>(this.service.create(reqAgent), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String uuid) {
        this.service.delete(uuid);
        return new ResponseEntity<>(Map.of("message", "agent deleted successfully"), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ResAgent>> readAllPaginated(Pageable pageable) {
        return new ResponseEntity<>(this.service.readAllPaginated(pageable), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<ResAgent> update(@RequestBody @Valid ReqAgent reqAgent, @PathVariable String uuid) {
        return new ResponseEntity<>(this.service.update(reqAgent, uuid), HttpStatus.OK);
    }
}
