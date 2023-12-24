package com.myrh.dtos.requests;

import com.myrh.enums.Status;
import com.myrh.models.Recruiter;
import com.myrh.models.SeekerOffer;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ReqJobOffer {
    private UUID uuid;
    @NotNull(message = "title cannot be null")
    private String title;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "profile cannot be null")
    private String profile;
    @NotNull(message = "city cannot be null")
    private String city;
    @NotNull(message = "education level cannot be null")
    private String educationLevel;
    @Min(value = 0)
    @Max(value = 100000)
    private Float salary;
    private Status status;
    @NotNull(message = "recruiter cannot be null")
    private UUID recruiter;
}
