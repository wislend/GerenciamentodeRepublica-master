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

    public abstract View getView(int position, View convertView, ViewGroup parent, T item);
}
