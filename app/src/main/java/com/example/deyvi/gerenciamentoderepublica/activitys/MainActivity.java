package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.Base.BaseDrawer;
import com.example.deyvi.gerenciamentoderepublica.adapters.AdapterImovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.ciceromoura.loaderrecycler.BaseLoaderRecyclerViewAdapter;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseDrawer implements BaseLoaderRecyclerViewAdapter.OnLoaderListener{

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private AdapterImovel mAdapter;

    @AfterViews
    protected void init(){

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterImovel(setListImovelTest());
        mRecyclerView.setAdapter(mAdapter);
    }

    List<Imovel>setListImovelTest(){
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

    @Override
    public void onLoaderLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderFinished() {
        Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show();
    }
}
