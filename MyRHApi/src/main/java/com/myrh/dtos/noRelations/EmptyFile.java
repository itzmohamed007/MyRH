package com.myrh.dtos.noRelations;

import lombok.Data;

import java.util.UUID;

@Data
public class EmptyFile {
    private UUID uuid;
    private String name;
    private Long size;
    private String type;
    private String contentBase64;
}
