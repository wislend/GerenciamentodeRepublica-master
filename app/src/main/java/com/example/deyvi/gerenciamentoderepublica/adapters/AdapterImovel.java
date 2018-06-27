package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.ArrayList;
import java.util.List;

public class AdapterImovel extends RecyclerView.Adapter<AdapterImovel.ImovelViewHolder>{

    private List<Imovel> mListImovel = new ArrayList<>();

    public AdapterImovel(List<Imovel> listImovel) {
        this.mListImovel = listImovel;
    }

    @NonNull
    @Override
    public ImovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImovelViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_card_imovel, parent, false));

    }



    @Override
    public void onBindViewHolder(@NonNull ImovelViewHolder holder, int position) {
    /*  holder.txtNomeImovel.setText(mListImovel.get(position).getNome());
      holder.txtQuantQuarto.setText(String.valueOf(mListImovel.get(position).getQuantQuartos()));
      holder.txtValorImovel.setText(String.valueOf(mListImovel.get(position).getValor()));*/
    }

    @Override
    public int getItemCount() {
        return mListImovel != null ? mListImovel.size() : 0;
    }

     class ImovelViewHolder extends RecyclerView.ViewHolder {

      /*  RobotoTextView txtNomeImovel;
        RobotoTextView txtValorImovel;
        ImageView imgFoto;
        RobotoTextView txtQuantQuarto;
*/
        ImovelViewHolder(View v) {
            super(v);
          /*  txtNomeImovel = v.findViewById(R.id.txtNomeImovel);
            txtQuantQuarto = v.findViewById(R.id.txtQuantQuarto);
            txtValorImovel = v.findViewById(R.id.txtValorImovel);
            imgFoto = v.findViewById(R.id.imgFoto);*/
        }
    }
}
