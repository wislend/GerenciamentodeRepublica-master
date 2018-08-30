package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView_;

import java.util.ArrayList;
import java.util.List;

public class ImoveisAdapter extends BaseAdapterW<Imovel> {

    private CardQuartosCadastradosRowView.OnClickManipulacaoImoveis mOnClickManipulacaoImoveis;

    public ImoveisAdapter(@NonNull Context context,List<Imovel> listImoveis) {
        super(context, listImoveis);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent, Imovel item) {

        CardQuartosCadastradosRowView cardQuartosCadastradosRowView;
        if (view == null) {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView_.build(getContext());
        } else {
            cardQuartosCadastradosRowView = CardQuartosCadastradosRowView.cast(view);
        }
        cardQuartosCadastradosRowView.setOnClickManipulacaoImoveis(mOnClickManipulacaoImoveis);
        cardQuartosCadastradosRowView.bind(item, position);

        return cardQuartosCadastradosRowView;
    }


    public void setImoveis(List<Imovel> imoveis) {
        if (imoveis != null) {
            imoveis.addAll(imoveis);
        }

        notifyDataSetChanged();
    }



    public CardQuartosCadastradosRowView.OnClickManipulacaoImoveis getOnClickManipulacaoImoveis() {
        return mOnClickManipulacaoImoveis;
    }

    public void setOnClickManipulacaoImoveis(CardQuartosCadastradosRowView.OnClickManipulacaoImoveis onClickManipulacaoImoveis) {
        this.mOnClickManipulacaoImoveis = onClickManipulacaoImoveis;
    }


    @Override
    public void onItemClicked(View view, Imovel item, int position) {
        if (mOnClickManipulacaoImoveis != null){
        mOnClickManipulacaoImoveis.onClickEdite((CardQuartosCadastradosRowView)view,position,item);
        mOnClickManipulacaoImoveis.onClickDelete(item);
        mOnClickManipulacaoImoveis.onClickAddQuarto();
        }


    }
}
