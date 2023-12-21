package com.myrh.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ValidationCode {
    @Id
    @Column(columnDefinition = "uuid default gen_random_uuid()")
    private UUID uuid;
    private String code;
    private LocalDateTime date;
}
