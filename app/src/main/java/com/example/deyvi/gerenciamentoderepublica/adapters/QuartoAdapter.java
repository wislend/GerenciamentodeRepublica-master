package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseAdapterW;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardImoveisCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView_;

import java.util.List;

public class QuartoAdapter extends BaseAdapterW<Quarto> {



    private CardQuartosCadastradosRowView.OnClickManipulacaoQuartos onClickManipulacaoQuartos;

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
        cardQuartosCadastradosRowView.setOnClickManipulacaoQuartos(onClickManipulacaoQuartos);

        return cardQuartosCadastradosRowView;
    }

    public CardQuartosCadastradosRowView.OnClickManipulacaoQuartos getOnClickManipulacaoQuartos() {
        return onClickManipulacaoQuartos;
    }

    public void setOnClickManipulacaoQuartos(CardQuartosCadastradosRowView.OnClickManipulacaoQuartos onClickManipulacaoQuartos) {
        this.onClickManipulacaoQuartos = onClickManipulacaoQuartos;
    }


    @Override
    public void remove(@Nullable Quarto quarto) {
        super.remove(quarto);
        notifyDataSetChanged();
    }


    @Override
    public void onItemClicked(View view, Quarto item, int position) {
        if (onClickManipulacaoQuartos != null){
            onClickManipulacaoQuartos.onClickEdite((CardQuartosCadastradosRowView) view,position,item);
            onClickManipulacaoQuartos.onClickDelete(item);
            onClickManipulacaoQuartos.onClickDetailsQuarto(item);
            onClickManipulacaoQuartos.onClickFavoritar(item);
        }
    }
}
