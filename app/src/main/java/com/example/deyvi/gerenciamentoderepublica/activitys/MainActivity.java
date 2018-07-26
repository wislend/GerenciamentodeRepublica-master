package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.AdapterImovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById
    FloatingActionButton fab;

    @AfterViews
    protected void init() {
        AdapterImovel mAdapter = new AdapterImovel(this,setListImovelTest());
        listView.setAdapter(mAdapter);
    }


    @Click
    void fab() {
        CadastroQuartoActivity_.intent(this).start();
    }

        List<Imovel> setListImovelTest() {
        List<Imovel> listImovel = new ArrayList<>();
        Imovel imovel1 = new Imovel();
        imovel1.setNome("Recanto Feliz");
        imovel1.setQuantQuartos(2);
        imovel1.setValor(150);
        Imovel imovel2 = new Imovel();
        imovel2.setNome("Pousada Pra quem tem");
        imovel2.setQuantQuartos(2);
        imovel2.setValor(150);
        listImovel.add(imovel1);
        listImovel.add(imovel2);
        return listImovel;
    }


}
