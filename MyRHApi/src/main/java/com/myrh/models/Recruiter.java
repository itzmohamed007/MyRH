package com.myrh.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Recruiter extends User {
    private String password;
    @OneToOne
    private File image;
    @OneToMany(mappedBy = "recruiter", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOffer> jobOffers;
}
