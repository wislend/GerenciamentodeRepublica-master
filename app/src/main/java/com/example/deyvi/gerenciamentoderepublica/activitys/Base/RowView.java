package com.example.deyvi.gerenciamentoderepublica.activitys.Base;



import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public abstract class RowView<T> extends LinearLayout {

    private Integer position = 0;
    private long objectID;

    public RowView(Context context) {
        super(context);
    }

    public RowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getPosition() {
        return position;
    }

    protected void setPosition(int position) {
        this.position = position;
    }

    public long getObjectID() {
        return objectID;
    }

    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }

    /**
     * Meto onde sera preenchida a linha
     *
     * @param item
     * @param position
     */
    @CallSuper
    public void bind(T item, int position) {
        setPosition(position);
    }

    /**
     * Faz um cast de uma view para RowView
     *
     * @param view View a ser convertida
     * @param <V>  Tipo de view que deve ser especialização de RowView
     * @return
     */
    public static <V extends RowView> V cast(@NonNull View view) {
        return (V) view;
    }

    @Override
    public Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("pos", getPosition());

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            if (bundle.containsKey("pos")) {
                setPosition(bundle.getInt("pos"));
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
