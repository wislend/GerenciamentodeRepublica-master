package com.example.deyvi.gerenciamentoderepublica.views.row;

import android.content.Context;
import android.util.AttributeSet;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.RowView;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.google.android.flexbox.FlexboxLayout;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.row_checked_view)
public class CheckedsRow  extends RowView<Movel> {

    @ViewById
    FlexboxLayout flexBoxConteaner;

    public CheckedsRow(Context context) {
        super(context);
    }

    public CheckedsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckedsRow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


}
