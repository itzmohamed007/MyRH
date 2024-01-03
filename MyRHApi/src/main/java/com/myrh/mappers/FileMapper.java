package com.myrh.mappers;

import com.myrh.dtos.noRelations.EmptyFile;
import com.myrh.dtos.responses.ResFile;
import com.myrh.exceptions.BadRequestException;
import com.myrh.models.File;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.pool.TypePool;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class FileMapper {
    private final ModelMapper modelMapper;

    public File mapFileToUploadFormat(MultipartFile plainFile) {
        try {
            File file = new File();
            file.setName(plainFile.getOriginalFilename());
            file.setSize(plainFile.getSize());
            file.setContent(plainFile.getBytes());
            file.setType(plainFile.getContentType());
            return file;
        } catch (IOException e) { throw new BadRequestException(e.getMessage()); }
    }

    public File mapFileToUploadFormat(ResFile plainFile) {
        File file = new File();
        file.setUuid(plainFile.getUuid());
        file.setName(plainFile.getName());
        file.setSize(plainFile.getSize());
        file.setContent(plainFile.getContent());
        file.setType(plainFile.getType());
        return file;
    }

    public ResFile mapFileToDownloadFormat(File file) {
        ResFile resFile = new ResFile();
        resFile.setUuid(file.getUuid());
        resFile.setName(file.getName());
        resFile.setType(file.getType());
        resFile.setSize(file.getSize());
        resFile.setContent(file.getContent());
        return resFile;
    }

    public EmptyFile mapFileToFetchFormat(File file) {
        EmptyFile emptyFile = modelMapper.map(file, EmptyFile.class);
        emptyFile.setContentBase64(Base64.getEncoder().encodeToString(file.getContent()));
        return emptyFile;
    }
}
