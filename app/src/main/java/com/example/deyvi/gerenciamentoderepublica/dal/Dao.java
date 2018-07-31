package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.Model;

public abstract class Dao<T extends Model>   {


    public Long save(T objeto) {
        return objeto.save();
    }


    public abstract boolean update(T objeto);


    public boolean delete(T objeto) {
        return false;
    }


    public T select(T objeto) {
        return null;
    }


    public boolean exists(T objeto) {
        return false;
    }
}
