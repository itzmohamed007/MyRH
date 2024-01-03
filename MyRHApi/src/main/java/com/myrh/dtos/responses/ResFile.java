package com.myrh.dtos.responses;

import lombok.Data;

import java.util.UUID;

@Data
public class ResFile {
    private UUID uuid;
    private String name;
    private String type;
    private Long size;
    byte[] content;
}
