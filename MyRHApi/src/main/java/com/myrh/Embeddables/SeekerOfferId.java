package com.myrh.Embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SeekerOfferId implements Serializable {
    @Column(name = "seeker_uuid")
    private String seekerUuid;
    @Column(name = "offer_uuid")
    private Integer offerUuid;
}
