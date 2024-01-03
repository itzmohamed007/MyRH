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
public class JobSeeker extends User {
    @Column(nullable = false, unique = true)
    private String identifier;
    @OneToOne
    private File resume;
    @OneToMany(mappedBy = "jobSeeker")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Applying> seekerOffers;
}
