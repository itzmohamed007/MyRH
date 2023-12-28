package com.myrh.dtos.responses;

import com.myrh.dtos.noRelations.EmptyJobOffer;
import com.myrh.dtos.noRelations.EmptyJobSeeker;
import lombok.Data;

@Data
public class ResSeekerOffer {
    private String letter;
    private EmptyJobSeeker jobSeeker;
    private EmptyJobOffer jobOffer;
}
