package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.Model;

public abstract class Dao<T extends Model>   {


    public abstract Long save(T objeto);


    public abstract boolean update(T objeto);


    public abstract boolean delete(T objeto);


    public T select(T objeto) {
        return null;
    }

    public T select(Long id) {
        return null;
    }



    public boolean exists(T objeto) {
        return false;
    }

    public boolean exists(String nome) {
        return false;
    }



    public boolean exists(Integer numero) {
        return false;
    }



}
