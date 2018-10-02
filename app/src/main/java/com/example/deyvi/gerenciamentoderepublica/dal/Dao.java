package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.Model;

import java.util.List;

public abstract class Dao<T extends Model>   {


    public abstract Long save(T objeto);


    public void update(T objeto) {

    }

    public void update(String nome, boolean checado) {

    }


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

    public boolean exists(String nome)  {
        return false;
    }

    public boolean exists(Integer numero) {
        return false;
    }

    public List<T> selectAll(){
        return null;
    }

}
