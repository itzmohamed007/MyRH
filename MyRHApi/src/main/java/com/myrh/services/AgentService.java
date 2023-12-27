package com.myrh.services;

import com.myrh.dtos.requests.ReqAgent;
import com.myrh.dtos.responses.ResAgent;
import com.myrh.dtos.responses.ResRecruiter;
import com.myrh.exceptions.IllegalConstraintViolation;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.Agent;
import com.myrh.models.Recruiter;
import com.myrh.repositories.AgentRepository;
import com.myrh.services.interfaces.IAgentService;
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
public class AgentService implements IAgentService {
    private final AgentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResAgent read(String stringUuid) {
        UUID uuid = Utils.parseStringToUuid(stringUuid);
        Agent agent= repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("No agent was found with uuid "+ uuid));
        return modelMapper.map(agent, ResAgent.class);
    }

    @Override
    public List<ResAgent> readAll() {
        List<Agent> agents = repository.findAll();
        if(agents.isEmpty()) throw new ResourceNotFoundException("No agents were found");
        return agents.stream()
                .map(recruiter -> modelMapper.map(agents, ResAgent.class))
                .toList();
    }

    @Override
    public ResAgent create(ReqAgent reqAgent) {
        try {
            Agent savedAgent = repository.save(modelMapper.map(reqAgent, Agent.class));
            return modelMapper.map(savedAgent, ResAgent.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public void delete(String stringUuid) {
        UUID uuid = Utils.parseStringToUuid(stringUuid);
        this.checkAgentPresence(uuid);
        repository.deleteById(uuid);
    }

    @Override
    public ResAgent update(ReqAgent reqAgent, String stringUuid) {
        try {
            UUID parsedRecruiterUuid = Utils.parseStringToUuid(stringUuid);
            this.checkAgentPresence(parsedRecruiterUuid);
            reqAgent.setUuid(stringUuid);
            Agent agent = modelMapper.map(reqAgent, Agent.class);
            Agent updatedAgent = repository.save(agent);
            return modelMapper.map(updatedAgent, ResAgent.class);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public Page<ResAgent> readAllPaginated(Pageable pageable) {
        Page<Agent> agents = repository.findAll(pageable);
        if(agents.isEmpty()) throw new ResourceNotFoundException("No agents were found");
        return agents.map(recruiter -> modelMapper.map(agents, ResAgent.class));
    }

    private void checkAgentPresence(UUID uuid) {
        this.repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("no agent was found with uuid " + uuid));
    }
}
