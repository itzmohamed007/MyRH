package com.myrh.services.interfaces;

import java.util.List;

/*
    # X => Entity
    # Y => Primary key type
*/
public interface IEmptyService<X, Y>{
    Y read(Y y);
    Y create(X x);
    void delete(Y y);
}
