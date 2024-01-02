package com.myrh.dtos.noRelations;

import com.myrh.dtos.responses.ResFile;
import com.myrh.models.JobOffer;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmptyRecruiter extends EmptyUser {
    private String password;
    private ResFile image;
}
