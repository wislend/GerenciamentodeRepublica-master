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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.ArrayList;
import java.util.List;

import br.ciceromoura.loaderrecycler.BaseLoaderRecyclerViewAdapter;

public class RecyclerImoveisAdapter extends BaseLoaderRecyclerViewAdapter<Imovel,
        RecyclerImoveisAdapter.ViewHolder> {


    private List<Imovel> items;


    public RecyclerImoveisAdapter(@NonNull Context context, @NonNull LoaderManager loaderManager, int loaderId) {
        super(context, loaderManager, loaderId);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, Imovel imovel, int i) {
            viewHolder.txtNomeImovel.setText(imovel.getNome());
            viewHolder.txtQuantQuarto.setText(imovel.getQuantQuartos());
            viewHolder.txtValorImovel.setText(String.valueOf(imovel.getValor()));
    }

    @NonNull
    @Override
    protected List<Imovel> onLoadDataInBackground(@Nullable Bundle bundle) {
        List<Imovel> listImovel = new ArrayList<>();
        Imovel imovel1 = new Imovel();
        imovel1.setNome("ImovelBll 1");
        imovel1.setQuantQuartos(2);
        imovel1.setValor(150);
        Imovel imovel2 = new Imovel();
        imovel1.setNome("ImovelBll 2");
        imovel1.setQuantQuartos(2);
        imovel1.setValor(150);
        listImovel.add(imovel1);
        listImovel.add(imovel2);
        return listImovel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.row_imoveis, parent, false);
        return new ViewHolder(v);
    }

        public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeImovel;
        ImageView imgMenu;
        TextView txtValorImovel;
        ImageView imgFoto;
        TextView txtQuantQuarto;

        public ViewHolder(View v) {
            super(v);
            txtNomeImovel = v.findViewById(R.id.txtNomeImovel);
            txtQuantQuarto = v.findViewById(R.id.txtQuantQuarto);
            txtValorImovel = v.findViewById(R.id.txtValorImovel);
            imgFoto = v.findViewById(R.id.imgFoto);
        }
    }
}
