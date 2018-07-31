package com.example.deyvi.gerenciamentoderepublica.dal;

public interface DaoOperation<T> {

    boolean save(T objeto);
    boolean update(T objeto);
    boolean delete(T objeto);
    T select(T objeto);
    boolean exists(T objeto);
}
