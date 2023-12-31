package com.myrh.services;

import com.myrh.dtos.noRelations.EmptyFile;
import com.myrh.dtos.responses.ResFile;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.mappers.FileMapper;
import com.myrh.models.File;
import com.myrh.repositories.FileRepository;
import com.myrh.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService {
    private final FileRepository repository;
    private final FileMapper fileMapper;

    @Override
    public File upload(MultipartFile plainFile) {
        return repository.save(fileMapper.mapFileToUploadFormat(plainFile));
    }

    @Override
    public EmptyFile fetch(UUID uuid) {
        File file =  repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("file not found with uuid " + uuid));
        return this.fileMapper.mapFileToFetchFormat(file);
    }

    @Override
    public ResFile download(UUID uuid) {
        File file =  repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("file not found with uuid " + uuid));
        return fileMapper.mapFileToDownloadFormat(file);
    }
}
