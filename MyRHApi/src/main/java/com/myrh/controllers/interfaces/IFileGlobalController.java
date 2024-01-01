package com.myrh.controllers.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

// X => request dto
// Y => response dto
// Z => primary key type
// M => MultipartFile
public interface IFileGlobalController<X, Y, Z, M> {
    public ResponseEntity<Y> read(Z z);
    public ResponseEntity<List<Y>> readAll();
    public ResponseEntity<Y> create(X x, M m);
    public ResponseEntity<Map<String, String>> delete(Z z);
    public ResponseEntity<Page<Y>> readAllPaginated(Pageable pageable);
    public ResponseEntity<Y> update(X x, Z z);
}
