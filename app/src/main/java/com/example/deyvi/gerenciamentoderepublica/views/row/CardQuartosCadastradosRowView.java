package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.RowView;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.flexbox.FlexboxLayout;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EViewGroup(R.layout.row_quartos_imovel)
public class CardQuartosCadastradosRowView extends RowView<Quarto> {


    @ViewById
    ImageView imgFoto;

    @ViewById
    RobotoTextView actionEdit;

    @ViewById
    ImageButton imgFavorito;

    @ViewById
    ImageButton imgDelete;

    @ViewById
    TextView txtNomeQuarto;

    @ViewById
    FlexboxLayout containerMoveis;

   private List<Movel> listMoveis;

    private Context context;

    //bll
    private Moveis moveis;

    int chId = 1000;

    OnClickManipulacaoImoveis onClickManipulacaoImoveis;

    public CardQuartosCadastradosRowView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void bind(final Quarto item, final int position) {
        super.bind(item, position);
        imgFoto.setImageResource(R.drawable.foto_indisponivel);
        txtNomeQuarto.setText("Quarto com suite");
        moveis = new Moveis();
        listMoveis = moveis.listMoveis(item.getId());
        configureCheckedBox(listMoveis);

        //txtValorMovel.setText(String.valueOf(item.getValor()));


       /* imgDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null) {
                    getOnClickManipulacaoImoveis().onClickDelete(item);
                }
            }
        });

        actionEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null) {
                    getOnClickManipulacaoImoveis().onClickEdite(null,position,item);

                }
            }
        });

        imgFavorito.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null){
                    getOnClickManipulacaoImoveis().onClickAddQuarto();
                }
            }
        });

        imgFoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoImoveis() != null){
                    getOnClickManipulacaoImoveis().onClickDetailsQuarto(item);
                }
            }
        });*/


    }



    @SuppressLint("SetTextI18n")
    void configureCheckedBox(List<Movel> movel) {
        TextView[] tx = new TextView[movel.size()];
        for (int i = 0; i < movel.size(); i++) {
            tx[i] = new TextView(context);
            tx[i].setId(chId++);
            tx[i].setAllCaps(true);
            tx[i].setTypeface(tx[i].getTypeface(), Typeface.BOLD);
            tx[i].setText("#" + movel.get(i).getNome() + "  ");
            containerMoveis.addView(tx[i]);
        }
    }






        public interface OnClickManipulacaoImoveis {
        void onClickDelete(Quarto quarto);
        void onClickEdite(CardQuartosCadastradosRowView cadastradosRowView, int position, Quarto quarto);
        void onClickAddQuarto();
        void onClickDetailsQuarto(Quarto quarto);
    }

    public OnClickManipulacaoImoveis getOnClickManipulacaoImoveis() {
        return onClickManipulacaoImoveis;
    }

    public void setOnClickManipulacaoImoveis(OnClickManipulacaoImoveis onClickManipulacaoImoveis) {
        this.onClickManipulacaoImoveis = onClickManipulacaoImoveis;
    }


}
