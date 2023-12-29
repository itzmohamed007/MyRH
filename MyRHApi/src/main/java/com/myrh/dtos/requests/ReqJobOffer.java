package com.myrh.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqJobOffer {
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$\n", message = "please enter a valid uuid format")
    private String uuid;
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
    @NotNull(message = "status cannot be null")
    private String status;
    @NotNull(message = "recruiter cannot be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "please enter a valid UUID format")
    private String recruiter;
}
