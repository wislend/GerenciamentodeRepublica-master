package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.content.Context;
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


    public CardQuartosCadastradosRowView(Context context) {
        super(context);
    }

    @Override
    public void bind(Imovel item, int position) {
        super.bind(item, position);
        imgFoto.setImageResource(R.drawable.foto_indisponivel);
        txtNomeImovel.setText(item.getNome());
        txtQuantQuarto.setText(String.valueOf(item.getQuantQuartos()));
        txtValorImovel.setText(String.valueOf(item.getValor()));
    }
}
