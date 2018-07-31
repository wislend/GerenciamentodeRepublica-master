package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.widget.ListView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.LoadersAdapter;
import com.example.deyvi.gerenciamentoderepublica.adapters.ImoveisAdapter;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class ApresentacaoActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<List<Imovel>>{

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById
    FloatingActionButton fab;
    ImoveisAdapter mAdapter;

    @AfterViews
    protected void init() {
        getSupportLoaderManager().initLoader(1, null, this);
        mAdapter = new ImoveisAdapter(this,new ArrayList<Imovel>());
        listView.setAdapter(mAdapter);

    }


    @Click
    void fab() {
        CadastroQuartoActivity_.intent(this).start();
    }


    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Imovel>> onCreateLoader(int id, @Nullable Bundle args) {
        return new LoadersAdapter(ApresentacaoActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Imovel>> loader, List<Imovel> data) {
         mAdapter.setImoveis(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<List<Imovel>> loader) {
        mAdapter.setImoveis(new ArrayList<Imovel>());
    }


}
