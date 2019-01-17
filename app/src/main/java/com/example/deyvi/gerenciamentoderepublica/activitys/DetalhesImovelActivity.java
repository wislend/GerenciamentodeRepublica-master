package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.DetalhesImovelPagerAdapter;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_detalhes_quarto)
public class DetalhesImovelActivity extends BaseActivity {


    @ViewById
    ViewPager viewPager;
    @ViewById
    TabLayout tab_layout;
    boolean modEdit = false;



    @Extra
    Imovel imovel;

    public DetalhesImovelActivity() {
    }


    @AfterViews
    void afterView() {
        DetalhesImovelPagerAdapter detalhesImovelPagerAdapter = new DetalhesImovelPagerAdapter(this, getSupportFragmentManager(), imovel);
        viewPager.setAdapter(detalhesImovelPagerAdapter);
        tab_layout.setupWithViewPager(viewPager);

    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

}
