package com.example.deyvi.gerenciamentoderepublica.activitys.base;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.ArrayList;
import java.util.List;

public class LoadersAdapter extends AsyncTaskLoader<List<Imovel>> {

    public LoadersAdapter(Context context) {
        super(context);
    }

    @Override
    public List<Imovel> loadInBackground() {
        List<Imovel> listImovel = new ArrayList<>();
        Imovel imovel1 = new Imovel();
        imovel1.setNome("Recanto Feliz");
        imovel1.setQuantQuartos(2);
        imovel1.setValor(150);
        Imovel imovel2 = new Imovel();
        imovel2.setNome("Pousada Pra quem tem");
        imovel2.setQuantQuartos(2);
        imovel2.setValor(150);
        listImovel.add(imovel1);
        listImovel.add(imovel2);
        return listImovel;
    }


    @Override
    public void deliverResult(@Nullable List<Imovel> data) {
        super.deliverResult(data);
    }
}
