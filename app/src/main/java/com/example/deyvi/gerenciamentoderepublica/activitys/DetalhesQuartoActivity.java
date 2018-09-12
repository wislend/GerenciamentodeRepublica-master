package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.DetalhesQuartoPagerAdapter;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_detalhes_quarto)
public class DetalhesQuartoActivity extends BaseActivity {


    @ViewById
    Toolbar toolbar;
    @ViewById
    ViewPager viewPager;
    @ViewById
    TabLayout tab_layout;
    boolean modEdit = false;


    @AfterViews
    void afterView() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        DetalhesQuartoPagerAdapter detalhesQuartoPagerAdapter = new DetalhesQuartoPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(detalhesQuartoPagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (modEdit) {
            new AlertDialog.Builder(this)
                    .setTitle("Descartar seu cadastro?")
                    .setMessage("As informações preenchidas até agora serão perdidas.")
                    .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DetalhesQuartoActivity.super.onBackPressed();
                        }
                    }).setNegativeButton("Cancelar", null)
                    .show();
        } else {
            DetalhesQuartoActivity.super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ImovelChangeEvent{
      public final  Imovel imovel;

        public ImovelChangeEvent(Imovel imovel) {
            this.imovel = imovel;
        }
    }
}
