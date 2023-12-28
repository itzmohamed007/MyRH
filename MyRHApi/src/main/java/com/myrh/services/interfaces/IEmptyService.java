package com.myrh.services.interfaces;

import java.util.List;

/*
    # X => Entity
    # Y => Primary key type
*/
public interface IEmptyService<X, Y>{
    X read(Y y);
    X create(X x);
    void delete(Y y);
}
