package com.myrh.dtos.requests;

import com.myrh.dtos.noRelations.EmptyUser;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReqRecruiter extends EmptyUser {
    @NotNull(message = "password cannot be null")
    private String password;
    private String image;
}
