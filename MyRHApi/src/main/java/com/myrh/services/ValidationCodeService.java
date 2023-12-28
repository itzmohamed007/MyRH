package com.myrh.services;

import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.ValidationCode;
import com.myrh.repositories.ValidationCodeRepository;
import com.myrh.services.interfaces.IValidationCodeService;
import com.myrh.utils.Utils;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationCodeService implements IValidationCodeService {
    private final ValidationCodeRepository repository;

    @Override
    public String read(String stringUuid) {
        ValidationCode validationCode = repository.findById(Utils.parseStringToUuid(stringUuid))
                .orElseThrow(() -> new ResourceNotFoundException("Validation code not found"));
    }

    @Override
    public String create(ValidationCode validationCode) {
        return null;
    }

    @Override
    public void delete(String stringUuid) {

    }
}
