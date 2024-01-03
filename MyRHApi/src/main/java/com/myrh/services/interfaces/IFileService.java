package com.myrh.services.interfaces;

import com.myrh.dtos.noRelations.EmptyFile;
import com.myrh.dtos.responses.ResFile;
import com.myrh.models.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IFileService {
    File upload(MultipartFile plainFile);
    EmptyFile fetch(UUID uuid);
    ResFile download(UUID uuid);
    java.io.File downloadFile(UUID uuid);
}