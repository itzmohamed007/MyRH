package com.myrh.dtos.requests;

import com.myrh.dtos.noRelations.EmptyUser;
import com.myrh.models.SeekerOffer;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReqJobSeeker extends EmptyUser {
    private String identifier;
    @NotNull(message = "resume cannot be null")
    private String resume;
}
