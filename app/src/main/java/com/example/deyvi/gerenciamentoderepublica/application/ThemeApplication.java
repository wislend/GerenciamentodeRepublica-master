package com.example.deyvi.gerenciamentoderepublica.application;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;

import com.example.deyvi.gerenciamentoderepublica.R;

public final class ThemeApplication {

    public static Theme currenteTheme = getCurrentTheme() ;


   /* public static Theme getCurrenteTheme(){
        return Theme.getTheme();
    }
*/
    public static void onActivityCreateSetTheme(Activity activity, boolean withActionbar) {
        switch (getCurrentTheme()) {
            case DARK:
                //activity.setTheme(withActionbar ? R.style.AppTheme_Dark : R.style.AppTheme_Dark_NoActionBar);
                break;
            default:
                activity.setTheme(withActionbar ? R.style.AppTheme : R.style.AppTheme_NoActionBar);
                break;
        }
    }

    public static Theme getCurrentTheme() {
            return Theme.getTheme(PreferencesApplication.getCurrentTheme(GerenciadorAluguelApplication.getInstance()));
    }

    public static boolean isThemeDark() {
        return getCurrentTheme() == Theme.DARK;
    }

    public static boolean isThemeLight() {
        return getCurrentTheme() == Theme.LIGHT;
    }


    public enum Theme {
        DARK(1),
        LIGHT(2);

        private int themeValue;

        Theme(int i) {
            this.themeValue = i;
        }

        public static Theme getTheme(int id) {

            switch (id) {
                default:
                case 1:
                    return DARK;
                case 2:
                    return LIGHT;
            }
        }

        public int getValue() {
            return this.themeValue;
        }

    }



    /**
     * Chamado no método onCreate da Activity
     *
     * @param activity actviity a ter o tema aplicado
     */
    public static void onActivityCreateSetTheme(Activity activity, @StyleRes int themeLight, @StyleRes int themeDark) {
        switch (getCurrentTheme()) {
            case DARK:
                activity.setTheme(themeDark);
                break;
            default:
                activity.setTheme(themeLight);
                break;
        }
    }


    public static void onActivityRecreate(Activity activity) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
      //  activity.overridePendingTransition(R.anim.fade_in_change_theme, R.anim.fade_out_change_theme);
    }

    public static int getColor(Context context, @AttrRes int resId) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{resId});
        final int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static Drawable getDrawable(Context context, @AttrRes int resId) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{resId});
        final Drawable drawable = a.getDrawable(0);
        a.recycle();
        return drawable;
    }


/*
    @ColorRes
    public static int getColorNavDrawerItem() {

        switch (getCurrentTheme()) {
         *//*   default:
            case LIGHT:
                return R.color.grey_700;
            case DARK:
                return R.color.grey_400;*//*
        }

    }*/


/*

    public static int defineColorAlternationView(View view, int count) {

        //para cada bind, incrementamos o contador
        if (count % 2 == 0) {
            view.setBackgroundColor(getColor(view.getContext(), R.attr.alternationViewBackground));
        } else {
            view.setBackgroundColor(view.getResources().getColor(android.R.color.transparent));
        }

        return ++count;
    }
*/


}
