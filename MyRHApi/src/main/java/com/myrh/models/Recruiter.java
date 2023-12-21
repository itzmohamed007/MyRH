package com.myrh.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Recruiter extends User {
    private String password;
    private String image;
    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private Set<JobOffer> jobOffers;
}
