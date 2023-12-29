package com.myrh.dtos.noRelations;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmptyJobSeeker extends EmptyUser {
    private String identifier;
    private String resume;
}
