package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.example.deyvi.gerenciamentoderepublica.fragments.CadastroImovelFragment_;
import com.example.deyvi.gerenciamentoderepublica.fragments.CadastroLocatarioFragment_;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class CadastroStepAdapter extends AbstractFragmentStepAdapter {


    public CadastroStepAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position){
            case 1:
                return new CadastroImovelFragment_();

            case 2:
                return new CadastroImovelFragment_();
        }

        return  new CadastroLocatarioFragment_();



    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return new StepViewModel.Builder(context)
                .setTitle("Cadastro")
                .create();
    }
}
