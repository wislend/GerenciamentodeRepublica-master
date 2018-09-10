package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.QuartoAdapter;
import com.example.deyvi.gerenciamentoderepublica.bll.Quartos;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.gms.flags.IFlagProvider;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_quartos)
public class QuartosActivity extends BaseActivity {

    @Extra
    Imovel imovel;

    @ViewById
    ListView listView;

    private QuartoAdapter quartoAdapter;

    private List<Quarto> listQuartos;

    private Quartos quartos;

    @ViewById
    android.support.v7.widget.Toolbar toolbar;

    @AfterViews
    void afterView(){
        setSupportActionBar(toolbar);
        quartos = new Quartos();
        listQuartos = quartos.todosQuartos();

        if (listQuartos == null || listQuartos.size() == 0 ) {
            quartoAdapter = new QuartoAdapter(this,test());
            listView.setAdapter(quartoAdapter);
        }else {
            quartoAdapter = new QuartoAdapter(this,listQuartos);
            listView.setAdapter(quartoAdapter);
        }
    }

    List<Quarto> test() {

        List<Quarto> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Quarto quarto= new Quarto();
            quarto.setNome("Quarto" + i);
            list.add(quarto);
        }

        return list;
    }




}
