package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView_;

import java.util.List;

public class ImoveisAdapter extends BaseAdapterW<Imovel> {


    public ImoveisAdapter(@NonNull Context context, List<Imovel> imoveis) {
        super(context, imoveis);

    }

    @Override
    public View getView(int position, View view, ViewGroup parent, Imovel item) {

        CardQuartosCadastradosRowView cardQuartosCadastradosRowView;
        if (view == null) {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView_.build(getContext());
        } else {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView.cast(view);
        }
        cardQuartosCadastradosRowView.bind(item, position);

        return cardQuartosCadastradosRowView;
    }


    public void setImoveis(List<Imovel> imoveis) {
        imoveis.addAll(imoveis);
        notifyDataSetChanged();
    }


}
