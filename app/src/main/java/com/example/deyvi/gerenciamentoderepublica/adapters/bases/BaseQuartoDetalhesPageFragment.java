package com.example.deyvi.gerenciamentoderepublica.adapters.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.EventLog;

import com.example.deyvi.gerenciamentoderepublica.activitys.DetalhesQuartoActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.FragmentW;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


@EFragment
public class BaseQuartoDetalhesPageFragment extends FragmentW {


    @InstanceState
    protected Imovel imovel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Subscribe
    public final void onEvent(DetalhesQuartoActivity.ImovelChangeEvent event) {
        imovel = event.imovel;
        onImovelChanged(event.imovel);
    }


    protected void onImovelChanged(Imovel imovel) {

    }
}
