package com.myrh.utils;

import com.myrh.exceptions.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public abstract class Utils {
    public static UUID parseStringToUuid(String stringUuid) {
        try { return UUID.fromString(stringUuid); }
        catch (IllegalArgumentException e) { throw new BadRequestException("please enter a valid UUID format"); }
    }
}
