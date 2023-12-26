package com.myrh.dtos.noRelations;

import com.myrh.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class EmptyJobOffer {
    private UUID uuid;
    private String title;
    private String description;
    private String profile;
    private String city;
    private String educationLevel;
    private Float salary;
    private Status status;
}
