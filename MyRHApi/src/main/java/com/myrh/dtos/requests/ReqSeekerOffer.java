package com.myrh.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqSeekerOffer {
    private String letter;
    @NotNull(message = "job seeker uuid cannot be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "please enter a valid UUID format")
    private String jobSeeker;
    @NotNull(message = "job offer uuid cannot be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "please enter a valid UUID format")
    private String jobOffer;
}
