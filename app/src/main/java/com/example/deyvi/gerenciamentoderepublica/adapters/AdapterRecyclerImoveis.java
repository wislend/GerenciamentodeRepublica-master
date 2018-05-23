package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.List;

import br.ciceromoura.loaderrecycler.BaseLoaderRecyclerViewAdapter;

public class AdapterRecyclerImoveis extends BaseLoaderRecyclerViewAdapter<Imovel, AdapterRecyclerImoveis.ViewHolder> {

    public AdapterRecyclerImoveis(@NonNull Context context, @NonNull LoaderManager loaderManager,
                                  int loaderId) {
        super(context, loaderManager, loaderId);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, Imovel imovel, int i) {

    }

    @NonNull
    @Override
    protected List<Imovel> onLoadDataInBackground(@Nullable Bundle bundle) {
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(getContext()).inflate(R.layout.row_imoveis, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
