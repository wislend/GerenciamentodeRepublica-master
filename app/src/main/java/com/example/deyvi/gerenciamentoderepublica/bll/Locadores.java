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

    public boolean locadorExists(String email) throws Exception {
        return locadores.exists(email);
    }

    public Locador senhaValida(String email,String senha) throws Exception {
        return locadores.senhaValida(email,senha);
    }


}
