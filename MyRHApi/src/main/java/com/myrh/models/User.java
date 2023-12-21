package com.myrh.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public abstract class User {
    @Id
    @Column(columnDefinition = "uuid default gen_random_uuid()")
    private UUID uuid;
    private String fullName;
    private String email;
    private String phone;
}
