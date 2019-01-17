package com.example.deyvi.gerenciamentoderepublica.bll;

import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;

public class Enderecos {

    com.example.deyvi.gerenciamentoderepublica.dal.Enderecos oEnderecoDao;

    public Enderecos() {
        oEnderecoDao = new com.example.deyvi.gerenciamentoderepublica.dal.Enderecos();
    }


    public Long salvarEndereco(Endereco endereco){
        return oEnderecoDao.save(endereco);
    }

    public boolean enderecoExiste(String cep,String numero){
        return oEnderecoDao.exists(cep,numero);
    }

    public Endereco selectEndereco(Long id){
        return oEnderecoDao.select(id);
    }



}
