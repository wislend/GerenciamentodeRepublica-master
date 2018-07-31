package com.example.deyvi.gerenciamentoderepublica.dal;

import com.example.deyvi.gerenciamentoderepublica.entitys.BaseEntitys;

public class Enderecos extends BaseEntitys implements DaoOperation<Enderecos> {


    @Override
    public boolean save(Enderecos endereco) {
        return endereco.save(endereco);
    }

    @Override
    public boolean update(Enderecos endereco) {
        return false;
    }

    @Override
    public boolean delete(Enderecos endereco) {
        return false;
    }

    @Override
    public boolean select(Enderecos endereco) {
        return false;
    }

    @Override
    public boolean exists(Enderecos endereco) {
        return false;
    }
}
