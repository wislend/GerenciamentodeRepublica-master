package com.example.deyvi.gerenciamentoderepublica.bll;

import com.example.deyvi.gerenciamentoderepublica.dal.Imoveis;

public class imoveis {

    Imoveis oImoveisDao;

    public imoveis() {
        oImoveisDao = new Imoveis();
    }

    public boolean imovelExiste(String nome){
        if (nome.isEmpty()){
            return false;
        }
       return oImoveisDao.exists(nome);
    }

}
