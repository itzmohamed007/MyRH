package com.myrh.controllers.interfaces;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IGlobalController<X, Y, Z> extends IBasicController<X, Y, Z> {
    public ResponseEntity<Page<Y>> readAllPaginated(Pageable pageable);
    public ResponseEntity<Y> update(X x, Z z);
}