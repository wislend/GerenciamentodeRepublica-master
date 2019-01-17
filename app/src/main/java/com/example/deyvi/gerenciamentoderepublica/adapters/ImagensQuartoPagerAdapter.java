package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagensQuartoPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mResources;

    public ImagensQuartoPagerAdapter(Context context, List<String> resources) {
        mResources = resources;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    public void atualizarImagens(String caminho) {
        mResources.add(caminho);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        Picasso.get().load("file:///" + mResources.get(position)).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}
