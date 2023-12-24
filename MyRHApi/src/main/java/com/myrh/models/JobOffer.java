package com.myrh.models;

import com.myrh.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class JobOffer {
    @Id
    @Column(columnDefinition = "uuid default gen_random_uuid()")
    private UUID uuid;
    private String title;
    private String description;
    private String profile;
    private String city;
    private String educationLevel;
    private Float salary;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "VARCHAR NOT NULL DEFAULT 'pending'")
    private Status status;
    @ManyToOne
    private Recruiter recruiter;
    @OneToMany(mappedBy = "jobOffer")
    private Set<SeekerOffer> seekerOffers;
}
