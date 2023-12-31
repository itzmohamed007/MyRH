package com.myrh.repositories;

import com.myrh.models.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ValidationCodeRepository extends JpaRepository<ValidationCode, UUID> {
    Optional<ValidationCode> findValidationCodeByCode(String code);
}
