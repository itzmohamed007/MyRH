package com.myrh.dtos.requests;

import com.myrh.dtos.noRelations.EmptyUser;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReqJobSeeker extends EmptyUser {
    private String identifier;
    @NotNull(message = "resume cannot be null")
    private String resume;
}
