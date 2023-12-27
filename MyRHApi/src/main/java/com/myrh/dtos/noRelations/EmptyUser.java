package com.myrh.dtos.noRelations;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class EmptyUser {
    private String uuid;
    @NotNull(message = "full name cannot be null")
    private String fullName;
    @NotNull(message = "email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "please enter a valid email address")
    private String email;
    @NotNull(message = "phone cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "please enter a valid phone number")
    private String phone;
}