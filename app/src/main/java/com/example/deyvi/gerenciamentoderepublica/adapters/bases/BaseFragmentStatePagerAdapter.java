package com.example.deyvi.gerenciamentoderepublica.adapters.bases;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.Arrays;
import java.util.List;


public abstract class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    //Possui esse atributo para o caso do utilizador necessitar saber quando ocorre mudanca de páginas fora dessa classe
    private ViewPager.OnPageChangeListener onPageChangeListener;

    private Context mContext;
    private List<String> viewPagerTitles;
    private FragmentManager mFragmentManager;

    public final Context getContext() {
        return mContext;
    }

    public final void setContext(Context mContext) {
        this.mContext = mContext;
    }

    void setViewPagerTitles(List<String> viewPagerTitles) {
        this.viewPagerTitles = viewPagerTitles;
    }

    @Nullable
    public final List<String> getViewPagerTitles() {
        return viewPagerTitles;
    }

    public final void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    @Nullable
    public final ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    /**
     * Constroi um novo objeto BaseFragmentAdapterIon
     *
     * @param context         Contexto onde a view esta sendo criada
     * @param fragmentManager FragmentManager responsavel pelo processamento
     * @param viewPagerTitles Titulos dos ViewPagers
     */
    public BaseFragmentStatePagerAdapter(@NonNull Context context, @NonNull FragmentManager fragmentManager, @Nullable List<String> viewPagerTitles) {

        super(fragmentManager);
        mFragmentManager = fragmentManager;
        setContext(context);
        setViewPagerTitles(viewPagerTitles);
    }

    /**
     * Constroi um novo objeto BaseFragmentAdapter
     *
     * @param context         Contexto onde a view esta sendo criada
     * @param fragmentManager FragmentManager responsavel pelo processamento
     * @param titlesResId     ResourceID do vetor de titulos das Tabs
     */
    public BaseFragmentStatePagerAdapter(@NonNull Context context, @NonNull FragmentManager fragmentManager, @ArrayRes int titlesResId) {
        this(context, fragmentManager, Arrays.asList(context.getResources().getStringArray(titlesResId)));
    }


    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return viewPagerTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagerTitles.get(position);
    }

    @NonNull
    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }
}
