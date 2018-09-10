package com.example.deyvi.gerenciamentoderepublica.adapters.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.FragmentW;

import org.androidannotations.annotations.InstanceState;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseQuartoDetalhesPageFragment extends FragmentW {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    /*@Subscribe
    public final void onEvent(ConsultaProntaEntregaActivity.FilialConsultaChangedEvent event) {
        codFilial = event.filial.getCodFilial();
        onFilialChanged(event.filial);
    }


    protected void onFilialChanged(Filial filial) {

    }*/
}
