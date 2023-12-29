package com.myrh.dtos.noRelations;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmptySeekerOfferId {
    @NotNull(message = "seeker cannot be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "please enter a valid uuid format")
    private String seekerUuid;
    @NotNull(message = "seeker cannot be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "please enter a valid uuid format")
    private String offerUuid;
}
