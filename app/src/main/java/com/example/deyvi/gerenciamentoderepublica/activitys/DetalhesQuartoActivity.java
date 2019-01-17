package com.example.deyvi.gerenciamentoderepublica.activitys;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.entitys.ListGsonSerializer;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class DetalhesQuartoActivity extends CadastroQuartoActivity implements ImageClickListener {

    @Extra
    Quarto quarto;

    private List<String> listImagens = new ArrayList<>();


    @Override
    public void afterViews() {
        super.afterViews();
        listImagens = new ListGsonSerializer().deserialize(quarto.getImagens());
        popular();

    }


    void popular() {
        if (quarto != null) {
            if (listImagens != null && listImagens.size() != 0) {
                carouselView.setVisibility(View.VISIBLE);
                carouselView.setPageCount(listImagens.size());
                ImageListener imageListener = new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        Picasso.get().load(listImagens.get(position)).into(imageView);
                    }
                };

                carouselView.setImageListener(imageListener);
                carouselView.setImageClickListener(this);
            }
            edtIdentificacaoQuarto.setText(quarto.getNome());
            edtPreco.setText(String.valueOf(quarto.getPreco()));
            edtNumero.setText(String.valueOf(quarto.getNumero()));

        }

    }


        //Click da imagem
        @Override
        public void onClick(int position) {
            Toast.makeText(this, "clicou" + position, Toast.LENGTH_SHORT).show();
        }
}
