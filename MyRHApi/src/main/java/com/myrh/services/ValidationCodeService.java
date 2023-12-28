package com.myrh.services;

import com.myrh.exceptions.IllegalConstraintViolation;
import com.myrh.exceptions.ResourceNotFoundException;
import com.myrh.models.ValidationCode;
import com.myrh.repositories.ValidationCodeRepository;
import com.myrh.services.interfaces.IValidationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationCodeService implements IValidationCodeService {
    private final ValidationCodeRepository repository;

    @Override
    public ValidationCode read(String code) {
        return this.repository.findValidationCodeByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Validation code not found with code " + code));
    }

    @Override
    public ValidationCode create(ValidationCode validationCode) {
        try { return this.repository.save(validationCode); }
        catch (DataIntegrityViolationException e) { throw new IllegalConstraintViolation(e.getMessage()); }
    }

    @Override
    public void delete(String code) {
        this.repository.delete(this.read(code));
    }
}
