package com.example.deyvi.gerenciamentoderepublica.dal;

import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;

public class Locadores extends Dao<Locador> {
    
    @Override
    public Long save(Locador locador) {
        return locador.save();
    }

    @Override
    public boolean update(Locador locador) {
        return false;
    }

    @Override
    public boolean delete(Locador locador) {
        return false;
    }

}
