package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.RowView;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.row_card_imovel)
public class CardQuartosCadastradosRowView extends RowView<Imovel> {

    @ViewById
    TextView txtNomeImovel;
    @ViewById
    TextView txtValorImovel;
    @ViewById
    ImageView imgFoto;
    @ViewById
    TextView txtQuantQuarto;

    @ViewById
    View btnAdicionarQuarto;
    @ViewById
    View btnEdit;
    @ViewById
    View btnDelete;


    OnClickManipulacaoImoveis onClickManipulacaoImoveis;

    public CardQuartosCadastradosRowView(Context context) {
        super(context);
    }

    @Override
    public void bind(final Imovel item, final int position) {
        super.bind(item, position);
        imgFoto.setImageResource(R.drawable.foto_indisponivel);
        txtNomeImovel.setText(item.getNome());
        txtQuantQuarto.setText(String.valueOf(item.getQuantQuartos()));
        txtValorImovel.setText(String.valueOf(item.getValor()));


        btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null) {
                    getOnClickManipulacaoImoveis().onClickDelete(item);
                }
            }
        });

        btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null) {
                    getOnClickManipulacaoImoveis().onClickEdite(null,position,item);

                }
            }
        });

        btnAdicionarQuarto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null){
                    getOnClickManipulacaoImoveis().onClickAddQuarto();
                }
            }
        });



    }

    public interface OnClickManipulacaoImoveis {
        void onClickDelete(Imovel imovel);
        void onClickEdite(CardQuartosCadastradosRowView cadastradosRowView, int position, Imovel imovel);
        void onClickAddQuarto();
    }

    public OnClickManipulacaoImoveis getOnClickManipulacaoImoveis() {
        return onClickManipulacaoImoveis;
    }

    public void setOnClickManipulacaoImoveis(OnClickManipulacaoImoveis onClickManipulacaoImoveis) {
        this.onClickManipulacaoImoveis = onClickManipulacaoImoveis;
    }


}
