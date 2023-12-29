package com.myrh.models;

import com.myrh.Embeddables.ApplyingId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applying {
    @EmbeddedId
    private ApplyingId id;
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
