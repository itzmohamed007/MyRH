package com.myrh.dtos.responses;

import com.myrh.dtos.noRelations.EmptyRecruiter;
import com.myrh.enums.Status;
import com.myrh.models.Applying;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ResJobOffer {
    private UUID uuid;
    private String title;
    private String description;
    private String profile;
    private String city;
    private String educationLevel;
    private Float salary;
    private Status status;
    private EmptyRecruiter recruiter;
    private Set<Applying> seekerOffers;
}
