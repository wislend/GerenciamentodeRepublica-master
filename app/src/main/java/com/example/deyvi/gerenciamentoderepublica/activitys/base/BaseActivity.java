package com.example.deyvi.gerenciamentoderepublica.activitys.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;


@SuppressLint("Registered")
@EActivity
public class BaseActivity extends AppCompatActivity {

    @InstanceState
    protected boolean progressShowing;
    @InstanceState
    protected boolean progrableShowing;
    @InstanceState
    protected String messageProgress;
    private View viewLoading;
    private ProgressDialog mProgressDialog;
    private ViewGroup viewContent;
    private int mShortAnimationDuration;
    @AfterViews
    @CallSuper
    protected void onAfterViews() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @UiThread
    public void setLoading(boolean isLoading, final boolean modeInvisible) {



        final View hideView = isLoading ? viewContent : viewLoading;
        final View showView = isLoading ? viewLoading : viewContent;




        showView.setVisibility(View.VISIBLE);

        //if the show view is not the content view, we will display the loading view right now
        if (showView.getId() == viewLoading.getId()) {
            showView.setAlpha(1f);
            hideView.setVisibility(View.GONE);
        } else {
            // Set the "show" view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the anima
            // tion.
            showView.setAlpha(0f);

            // Animate the "show" view to 100% opacity, and clear any animation listener set on
            // the view. Remember that listeners are not limited to the specific animation
            // describes in the chained method calls. Listeners are set on the
            // ViewPropertyAnimator object for the view, which persists across several
            // animations.
            showView.animate()
                    .alpha(1f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            showView.setVisibility(View.VISIBLE);
                            showView.setAlpha(1);
                        }
                    });

            // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
            // to GONE as an optimization step (it won't participate in layout passes, etc.)
            hideView.animate()
                    .alpha(0f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            hideView.setVisibility(View.GONE);
                            hideView.setVisibility((modeInvisible && hideView == viewContent) ? View.INVISIBLE : View.GONE);
                        }
                    });
        }
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada
     *
     * @param message
     */
    public void showProgressDialog(String message) {

        showProgressDialog(message, false);
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada
     *
     * @param message
     */
    public void showProgressDialog(String message, boolean cancelable) {

        //não exibe nessas condições
        if (isFinishing() || isDestroyed()) {
            return;
        }

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (message != null && message.length() > 0) {
            messageProgress = message;
            mProgressDialog = ProgressDialog.show(this, "", Html.fromHtml(message), true, cancelable);
        } else {
            messageProgress = getString(getMessageTextProgressDialog());
            mProgressDialog = ProgressDialog.show(this, "", Html.fromHtml(messageProgress), true, cancelable);
        }


        Log.d("PROGRESS_DIAOG_ACT", "showProgressDialog()");
        progressShowing = true;
    }


    @StringRes
    public int getMessageTextProgressDialog() {
        return R.string.aguarde;
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada
     */
    public void showProgressDialog() {
        showProgressDialog(null);
    }


    @UiThread
    public void setLoading(boolean isLoading) {
        setLoading(isLoading, false);
    }

    public boolean isLoading() {

        return viewLoading != null && viewLoading.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }


    /**
     * Retira o progress
     */
    public void dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
            progrableShowing = false;
            progressShowing = false;

        }
    }



}
