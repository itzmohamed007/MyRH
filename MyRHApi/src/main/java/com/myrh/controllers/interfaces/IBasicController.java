package com.myrh.controllers.interfaces;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

// X => request dto
// Y => response dto
// Z => primary key type
public interface IBasicController<X, Y, Z> {
    public ResponseEntity<Y> read(Z z);
    public ResponseEntity<List<Y>> readAll();
    public ResponseEntity<Y> create(X x);
    public ResponseEntity<Map<String, String>> delete(Z z);
}
