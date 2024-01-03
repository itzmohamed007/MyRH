package com.myrh.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// X => request dto
// Y => response dto
// Z => primary key type
// M => MultipartFile
public interface IFileGlobalService<X, Y, Z, M> {
    Y read(Z z);
    List<Y> readAll();
    Y create(X x, M m);
    void delete(Z z);
    Y update(X x, Z z);
    Page<Y> readAllPaginated(Pageable pageable);
}
