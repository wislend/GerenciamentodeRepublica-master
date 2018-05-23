package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.Base.BaseDrawer;
import com.example.deyvi.gerenciamentoderepublica.adapters.AdapterRecyclerImoveis;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import br.ciceromoura.loaderrecycler.BaseLoaderRecyclerViewAdapter;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseDrawer implements BaseLoaderRecyclerViewAdapter.OnLoaderListener{

    private AdapterRecyclerImoveis adapter;


    @AfterViews
    protected void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterRecyclerImoveis(this, getSupportLoaderManager(), 1);
        recyclerView.setAdapter(adapter);

        //to listen to the loader callback
        adapter.setOnLoaderListener(this);
    }


    @Override
    public void onLoaderLoading() {

    }

    @Override
    public void onLoaderFinished() {

    }
}
