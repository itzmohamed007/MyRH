package com.myrh.Embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class SeekerOfferId implements Serializable {
    @Column(name = "seeker_uuid")
    private UUID seekerUuid;
    @Column(name = "offer_uuid")
    private UUID offerUuid;
}
