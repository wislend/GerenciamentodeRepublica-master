package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseAdapterW;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardImoveisCadastradosRowView;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardImoveisCadastradosRowView_;

import java.util.List;

public class ImoveisAdapter extends BaseAdapterW<Imovel> {

    private CardImoveisCadastradosRowView.OnClickManipulacaoImoveis mOnClickManipulacaoImoveis;

    public ImoveisAdapter(@NonNull Context context,List<Imovel> listImoveis) {
        super(context, listImoveis);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent, Imovel item) {

        CardImoveisCadastradosRowView cardImoveisCadastradosRowView;
        if (view == null) {
            cardImoveisCadastradosRowView = CardImoveisCadastradosRowView_.build(getContext());
        } else {
            cardImoveisCadastradosRowView = CardImoveisCadastradosRowView.cast(view);
        }
        cardImoveisCadastradosRowView.setOnClickManipulacaoImoveis(mOnClickManipulacaoImoveis);
        cardImoveisCadastradosRowView.bind(item, position);

        return cardImoveisCadastradosRowView;
    }


    public void setImoveis(List<Imovel> imoveis) {
        if (imoveis != null) {
            imoveis.addAll(imoveis);
        }

        notifyDataSetChanged();
    }



    public CardImoveisCadastradosRowView.OnClickManipulacaoImoveis getOnClickManipulacaoImoveis() {
        return mOnClickManipulacaoImoveis;
    }

    public void setOnClickManipulacaoImoveis(CardImoveisCadastradosRowView.OnClickManipulacaoImoveis onClickManipulacaoImoveis) {
        this.mOnClickManipulacaoImoveis = onClickManipulacaoImoveis;
    }


    @Override
    public void remove(@Nullable Imovel imovel) {
        super.remove(imovel);
        notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(View view, Imovel item, int position) {
        if (mOnClickManipulacaoImoveis != null){
        mOnClickManipulacaoImoveis.onClickEdite((CardImoveisCadastradosRowView)view,position,item);
        mOnClickManipulacaoImoveis.onClickDelete(item);
        mOnClickManipulacaoImoveis.onClickAddQuarto();
        }
    }
}
