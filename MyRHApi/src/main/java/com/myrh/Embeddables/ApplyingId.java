package com.myrh.Embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyingId implements Serializable {
    @Column(name = "seeker_uuid")
    private UUID seekerUuid;
    @Column(name = "offer_uuid")
    private UUID offerUuid;
}
