package com.example.deyvi.gerenciamentoderepublica.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseFragmentStatePagerAdapter;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseQuartoDetalhesPageFragment;
import com.example.deyvi.gerenciamentoderepublica.fragments.DetalhesQuartoFragment;
import com.example.deyvi.gerenciamentoderepublica.fragments.DetalhesQuartoFragment_;
import com.example.deyvi.gerenciamentoderepublica.fragments.GastosQuartoFragment_;
import com.example.deyvi.gerenciamentoderepublica.fragments.MelhoriasQuartoFragment_;


public class DetalhesQuartoPagerAdapter extends BaseFragmentStatePagerAdapter {

    private static final int DETALHES_PAGE_INDEX = 0;
    private static final int GASTOS_PAGE_INDEX = 1;
    private static final int MELHORIAS_PAGE_INDEX = 2;
    private BaseQuartoDetalhesPageFragment[] pages;


    public DetalhesQuartoPagerAdapter(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        super(context, fragmentManager, R.array.detalhe_quarto_page_titles);
        pages = new BaseQuartoDetalhesPageFragment[getCount()];
    }

    @Override
    public Fragment getItem(int i) {
        if (pages[i] == null) {
            switch (i) {
                case MELHORIAS_PAGE_INDEX:
                    pages[i] = MelhoriasQuartoFragment_.builder().build();
                    break;
                case GASTOS_PAGE_INDEX:
                    pages[i] = GastosQuartoFragment_.builder().build();
                    break;
                case DETALHES_PAGE_INDEX:
                default:
                    pages[i] = DetalhesQuartoFragment_.builder().build();
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
