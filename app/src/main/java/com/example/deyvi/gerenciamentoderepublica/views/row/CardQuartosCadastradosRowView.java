package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.RowView;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.ListGsonSerializer;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EViewGroup(R.layout.row_quartos_imovel)
public class CardQuartosCadastradosRowView extends RowView<Quarto>  {



    @ViewById
    ImageButton imgFavorito;

    @ViewById
    ImageButton imgDelete;

    @ViewById
    TextView txtNomeQuarto;

    @ViewById
    FlexboxLayout containerMoveis;

    @ViewById
    CarouselView carouselView;

    @ViewById
    RobotoTextView txtValorQuarto;
    @ViewById
    RobotoTextView txtStatus;
    @ViewById
    ImageButton  imgEditar;

    @ViewById
    ImageView imgFoto;


   private List<Movel> listMoveis;

    private Context context;

    int chId = 1000;

    OnClickManipulacaoQuartos onClickManipulacaoQuartos;

    public CardQuartosCadastradosRowView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void bind(final Quarto item, final int position) {
        super.bind(item, position);
        carouselView.setVisibility(View.VISIBLE);
        final List<String> list = new ListGsonSerializer().deserialize(item.getImagens());

        if (list.size() != 0){
            carouselView.setPageCount(list.size());
            ImageListener imageListener = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.get().load(list.get(position)).into(imageView);
                }
            };

            carouselView.setImageListener(imageListener);
        }



        txtNomeQuarto.setText(item.getNome());
        txtValorQuarto.setText(String.valueOf(item.getPreco()));
        txtStatus.setText(item.getStatus() == 0 ? "Status: Vago" : "Status: Ocupado");
        Moveis moveis = new Moveis();
        listMoveis = moveis.listMoveis(item.getId());
        if(listMoveis!= null) {
            configureCheckedBox(listMoveis);
        }






        imgDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoQuartos() != null) {
                    getOnClickManipulacaoQuartos().onClickDelete(item);
                }
            }
        });

        imgEditar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoQuartos() != null) {
                    getOnClickManipulacaoQuartos().onClickEdite(null,position,item);

                }
            }
        });

        imgFavorito.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoQuartos() != null){
                    getOnClickManipulacaoQuartos().onClickFavoritar(item);
                }
            }
        });

        carouselView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnClickManipulacaoQuartos() != null){
                    getOnClickManipulacaoQuartos().onClickDetailsQuarto(item);
                }
            }
        });


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




    public interface OnClickManipulacaoQuartos {
        void onClickDelete(Quarto quarto);
        void onClickEdite(CardQuartosCadastradosRowView cadastradosRowView, int position, Quarto quarto);
        void onClickDetailsQuarto(Quarto quarto);
        void onClickFavoritar(Quarto quarto);
    }

    public OnClickManipulacaoQuartos getOnClickManipulacaoQuartos() {
        return onClickManipulacaoQuartos;
    }

    public void setOnClickManipulacaoQuartos(OnClickManipulacaoQuartos onClickManipulacaoQuartos) {
        this.onClickManipulacaoQuartos = onClickManipulacaoQuartos;
    }


}
