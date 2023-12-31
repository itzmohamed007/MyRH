package com.myrh.dtos.responses;

import lombok.Data;

@Data
public class ResFile {
    private String name;
    private String type;
    byte[] content;
}
