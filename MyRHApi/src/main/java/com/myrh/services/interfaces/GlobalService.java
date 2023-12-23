package com.myrh.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// X => request dto
// Y => response dto
// Z => primary key type
public interface GlobalService<X, Y, Z> {
    Y read(Z z);
    List<Y> readAll();
    Y create(X x);
    void delete(Z z);
}
