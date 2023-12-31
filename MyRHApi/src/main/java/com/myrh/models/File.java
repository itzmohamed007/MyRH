package com.myrh.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private Long size;
    private String type;
    @Lob @Column(nullable = false)
    private byte[] content;
}
