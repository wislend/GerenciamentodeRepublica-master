package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapterW<T> extends android.widget.ArrayAdapter<T> {

    public BaseAdapterW(@NonNull Context context, List<T> items) {
        super(context, -1, items);
    }

    @NonNull
    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, getItem(position));
    }

    public void onItemClicked(View view, T item, int position) {

        /**
         * Se por acaso, este método estiver trazendo sempre a posição 0 para um item clicado,
         * verificar se a extensão de RowView está setando a position no método bind.
         * Basta chamar super.bind(item, position) para que a posição seja setada
         */
    }


    public abstract View getView(int position, View convertView, ViewGroup parent, T item);
}
