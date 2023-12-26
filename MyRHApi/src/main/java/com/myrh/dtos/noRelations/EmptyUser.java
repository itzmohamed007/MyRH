package com.myrh.dtos.noRelations;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class EmptyUser {
    private UUID uuid;
    private String fullName;
    private String email;
    private String phone;
}