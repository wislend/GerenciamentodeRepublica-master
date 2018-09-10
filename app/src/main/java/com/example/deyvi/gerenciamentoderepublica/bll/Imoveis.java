package com.example.deyvi.gerenciamentoderepublica.bll;


import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.List;

public class Imoveis {

    com.example.deyvi.gerenciamentoderepublica.dal.Imoveis oImoveisDao;

    public Imoveis() {
        oImoveisDao = new com.example.deyvi.gerenciamentoderepublica.dal.Imoveis();
    }

    public boolean imovelExiste(String nome){
        if (nome.isEmpty()){
            return false;
        }
       return oImoveisDao.exists(nome);
    }

    public List<Imovel> listImoveis(){
      return  oImoveisDao.selectAll();
    }


    public Long salvarImovel(Imovel imovel){
        return oImoveisDao.save(imovel);
    }

    public void deleteImovel(Imovel imovel){
        oImoveisDao.delete(imovel);
    }
}
