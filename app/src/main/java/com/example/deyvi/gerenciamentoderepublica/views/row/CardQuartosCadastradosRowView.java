package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.content.Context;
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

    private List<Movel> listMoveis = new ArrayList<>();

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
        txtNomeQuarto.setText(item.getNome());
        moveis = new Moveis();
        listMoveis = moveis.todosMoveis();
        configureCheckedBox();

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



    void configureCheckedBox() {
        CheckBox[] ch = new CheckBox[listMoveis.size()];
        for (int i = 0; i < listMoveis.size(); i++) {
            ch[i] = new CheckBox(context);
            ch[i].setId(chId++);
            ch[i].setText(listMoveis.get(i).getNome());
            ch[i].setChecked(listMoveis.get(i).isChecked());
            containerMoveis.addView(ch[i]);
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
