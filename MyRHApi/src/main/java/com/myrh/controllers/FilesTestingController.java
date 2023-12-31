package com.myrh.controllers;

import com.myrh.dtos.noRelations.EmptyFile;
import com.myrh.dtos.responses.ResFile;
import com.myrh.models.File;
import com.myrh.services.interfaces.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FilesTestingController {
    private final IFileService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> upload(@RequestBody MultipartFile file) {
        return new ResponseEntity<>(Map.of("fileUuid", service.upload(file).getUuid().toString()), HttpStatus.CREATED);
    }

    @GetMapping("/download/{uuid}")
    public ResponseEntity<byte[]> download(@PathVariable UUID uuid) {
        ResFile file = service.download(uuid);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<>(file.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/fetch/{uuid}")
    public ResponseEntity<EmptyFile> fetch(@PathVariable UUID uuid) {
        return new ResponseEntity<>(service.fetch(uuid), HttpStatus.OK);
    }

    @GetMapping("/preview/{uuid}")
    public ResponseEntity<byte[]> preview(@PathVariable UUID uuid) {
        ResFile file = service.download(uuid);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(getFileType(file.getType())); // Set appropriate content type
        headers.setContentDispositionFormData("inline", file.getName()); // Change to "inline" for preview
        return new ResponseEntity<>(file.getContent(), headers, HttpStatus.OK);
    }

    private MediaType getFileType(String fileType) {
        switch (fileType) {
            case "image/jpg":
            case "image/jpeg":
                return MediaType.IMAGE_JPEG;
            case "image/png":
                return MediaType.IMAGE_PNG;
            case "application/pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
