package com.myrh.models;

import com.myrh.Embeddables.SeekerOfferId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class SeekerOffer {
    @EmbeddedId
    private SeekerOfferId id;
    private String letter;
    @ManyToOne
    @MapsId("seekerUuid")
    @JoinColumn(name = "seeker_uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JobSeeker jobSeeker;
    @ManyToOne
    @MapsId("offerUuid")
    @JoinColumn(name = "offer_uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JobOffer jobOffer;
}
