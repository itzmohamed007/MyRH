package com.myrh.dtos.responses;

import com.myrh.dtos.noRelations.EmptyFile;
import com.myrh.dtos.noRelations.EmptyJobOffer;
import com.myrh.dtos.noRelations.EmptyUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResRecruiter extends EmptyUser {
    private String password;
    private ResFile image;
    private Set<EmptyJobOffer> jobOffers;
}
