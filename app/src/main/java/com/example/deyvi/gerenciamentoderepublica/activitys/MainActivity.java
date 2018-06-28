package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.Base.BaseDrawer;
import com.example.deyvi.gerenciamentoderepublica.adapters.AdapterImovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseDrawer {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById
    FloatingActionButton fab;

    private AdapterImovel mAdapter;

    @AfterViews
    protected void init() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterImovel(setListImovelTest());
        mRecyclerView.setAdapter(mAdapter);

        fab.setScaleX(0);
        fab.setScaleY(0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                    android.R.interpolator.fast_out_slow_in);

            fab.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(1000)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(0)
                                    .scaleX(0)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        }


    }

    List<Imovel> setListImovelTest() {
        List<Imovel> listImovel = new ArrayList<>();
        Imovel imovel1 = new Imovel();
        imovel1.setNome("Recanto Feliz");
        imovel1.setQuantQuartos(2);
        imovel1.setValor(150);
        Imovel imovel2 = new Imovel();
        imovel2.setNome("Pousada Pra quem tem");
        imovel2.setQuantQuartos(2);
        imovel2.setValor(150);
        listImovel.add(imovel1);
        listImovel.add(imovel2);
        return listImovel;
    }


}
