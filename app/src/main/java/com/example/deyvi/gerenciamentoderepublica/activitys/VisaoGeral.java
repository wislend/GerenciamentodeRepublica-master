package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseDrawer;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.LoadersAdapter;
import com.example.deyvi.gerenciamentoderepublica.adapters.ImoveisAdapter;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_visao_geral)
public class VisaoGeral extends BaseDrawer implements
        LoaderManager.LoaderCallbacks<List<Imovel>>,
        CardQuartosCadastradosRowView.OnClickManipulacaoImoveis {


    @ViewById(R.id.listView)
    ListView listView;
    ImoveisAdapter mImoveisAdapter;



    @ViewById
    Toolbar toolbar;

    @AfterViews
    void init() {

        getSupportLoaderManager().initLoader(1, null, this);
        mImoveisAdapter = new ImoveisAdapter(this, test());
        listView.setAdapter(mImoveisAdapter);
        mImoveisAdapter.setOnClickManipulacaoImoveis(this);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    List<Imovel> test() {
        Imovel imovel = new Imovel();
        List<Imovel> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            imovel.setNome("imovel" + i);
            imovel.setJurosDia(100);
            imovel.setJurosMes(120);
            imovel.setAlugado(true);
            imovel.setQuantQuartos(15);
            imovel.setValor(155500);
            list.add(imovel);
        }

        return list;
    }


    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Imovel>> onCreateLoader(int id, @Nullable Bundle args) {
        return new LoadersAdapter(VisaoGeral.this);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Imovel>> loader, List<Imovel> data) {
        mImoveisAdapter.setImoveis(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<List<Imovel>> loader) {
        mImoveisAdapter.setImoveis(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }


    @Override
    public void onClickDelete(Imovel imovel) {
        mImoveisAdapter.remove(imovel);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickEdite(CardQuartosCadastradosRowView cadastradosRowView, int position, Imovel imovel) {
        Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClickAddQuarto() {
        CadastroQuartoActivity_.intent(this).start();
    }

    @Override
    public void onClickDetailsQuarto(Imovel imovel) {
        Toast.makeText(this, "Detalhes", Toast.LENGTH_SHORT).show();
        DetalhesQuartoActivity_.intent(this).start();
    }
}
