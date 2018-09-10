package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseAdapterW;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView_;

import java.util.List;

public class QuartoAdapter extends BaseAdapterW<Quarto> {


    public QuartoAdapter(@NonNull Context context, List<Quarto> listQuartos) {
        super(context, listQuartos);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent, Quarto item) {

        CardQuartosCadastradosRowView cardQuartosCadastradosRowView;
        if (view == null) {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView_.build(getContext());
        } else {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView.cast(view);
        }
        cardQuartosCadastradosRowView.bind(item, position);

        return cardQuartosCadastradosRowView;
    }



}
