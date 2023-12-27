package com.myrh.dtos.requests;

import com.myrh.dtos.noRelations.EmptyUser;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReqRecruiter extends EmptyUser {
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "image path cannot be null")
    private String image;
}
