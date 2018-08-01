package com.example.deyvi.gerenciamentoderepublica.bll;

import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;

public class Locadores {

    private com.example.deyvi.gerenciamentoderepublica.dal.Locadores locadores;

    public Locadores(){
        locadores = new  com.example.deyvi.gerenciamentoderepublica.dal.Locadores();
    }

    public Long salvarLocador(Locador locador){
        return locadores.save(locador);
    }

    public boolean moradorExiste(String telefone){
        return locadores.exists(telefone);
    }

}
