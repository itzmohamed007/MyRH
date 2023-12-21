package com.myrh.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class JobSeeker extends User {
    @Column(nullable = false)
    private String identifier;
    private String resume;
    @OneToMany(mappedBy = "jobSeeker")
    private Set<SeekerOffer> seekerOffers;
}
