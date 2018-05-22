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
            case 0:
                return CadastroLocatarioFragment_.builder().build();

            case 1:
                return  CadastroImovelFragment_.builder().build();

            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {

        StepViewModel.Builder builder;

        switch (position) {
            case 0:
                builder = new StepViewModel.Builder(context)
                        .setTitle("Validação Inicial")
                        .setBackButtonVisible(false)
                        .setEndButtonLabel("Avançar");
                break;

            case 1:
                builder = new StepViewModel.Builder(context)
                        .setTitle("Cadastro Imovél")
                        .setBackButtonVisible(false)
                        .setEndButtonLabel("Concluir");
                break;

            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }


        return builder.create();

    }
}
