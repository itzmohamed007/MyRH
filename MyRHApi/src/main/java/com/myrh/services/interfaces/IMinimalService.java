package com.myrh.services.interfaces;

/*
    # X => Entity
    # Y => Primary key type
*/
public interface IMinimalService<X, Y>{
    X read(Y y);
    X create(X x);
    void delete(Y y);
}
