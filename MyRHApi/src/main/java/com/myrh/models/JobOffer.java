package com.myrh.models;

import com.myrh.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Recruiter recruiter;
    @OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SeekerOffer> seekerOffers;
}
