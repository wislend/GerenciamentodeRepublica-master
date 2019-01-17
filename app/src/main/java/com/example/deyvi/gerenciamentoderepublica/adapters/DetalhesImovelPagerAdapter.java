package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseFragmentStatePagerAdapter;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseImovelDetalhesPageFragment;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.fragments.DetalhesImovelFragment_;
import com.example.deyvi.gerenciamentoderepublica.fragments.GastosImovelFragment_;
import com.example.deyvi.gerenciamentoderepublica.fragments.MelhoriasImovelFragment_;


public class DetalhesImovelPagerAdapter extends BaseFragmentStatePagerAdapter {

    private static final int DETALHES_PAGE_INDEX = 0;
    private static final int GASTOS_PAGE_INDEX = 1;
    private static final int MELHORIAS_PAGE_INDEX = 2;
    private BaseImovelDetalhesPageFragment[] pages;
    private Imovel imovel;


    public DetalhesImovelPagerAdapter(@NonNull Context context, @NonNull FragmentManager fragmentManager,Imovel imovel) {
        super(context, fragmentManager, R.array.detalhe_quarto_page_titles);
        pages = new BaseImovelDetalhesPageFragment[getCount()];
        this.imovel = imovel;

    }

    @Override
    public Fragment getItem(int i) {
        if (pages[i] == null) {
            switch (i) {
                case MELHORIAS_PAGE_INDEX:
                    pages[i] = MelhoriasImovelFragment_.builder().build();
                    break;
                case GASTOS_PAGE_INDEX:
                    pages[i] = GastosImovelFragment_.builder().build();
                    break;
                case DETALHES_PAGE_INDEX:
                default:
                    pages[i] = DetalhesImovelFragment_.builder().imovel(imovel).build();
                    break;
            }
        }

        return pages[i];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        if (pages != null) {
            pages[position] = null;
        }
    }



}
