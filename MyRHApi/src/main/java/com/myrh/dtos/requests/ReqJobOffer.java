package com.myrh.dtos.requests;

import com.myrh.enums.Status;
import com.myrh.models.Recruiter;
import com.myrh.models.SeekerOffer;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ReqJobOffer {
    private UUID uuid;
    private String title;
    private String description;
    private String profile;
    private String city;
    private String educationLevel;
    private Float salary;
    private Status status;
    private UUID recruiter;
}
