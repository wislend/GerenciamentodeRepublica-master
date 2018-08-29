package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class BaseFragment extends Fragment  implements Step {

    private boolean mRequired = false;
    private ProgressDialog mProgressDialog;
    protected String TAG = this.getClass().getName();


    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    /**
     * Informa se um fragment está em um estado válido
     *
     * @return
     */
    public boolean isStateValid() {
        return isAdded() && getActivity() != null && !getActivity().isFinishing() && (Build
                .VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !getActivity().isDestroyed());
    }

    @StringRes
    public int getMessageTextProgressDialog() {
        return R.string.aguarde;
    }


    public final BaseActivity getBaseActivity() {
        if (getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        }

        throw new RuntimeException("A Activity atual não é uma especificação de BaseActivity");
    }

    public final void dismissProgressDialog() {

        if (!isStateValid()) {
            return;
        }

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
            Log.d("PROGRESS_DIAOG_FRAG", "dismissProgressDialog()");
        }


    }


    /**
     * Diz se o Progress Dialog está visível ou não
     *
     * @return
     */
    public boolean isProgressDialogShowing() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }



    public final void showProgressDialog() {
        showProgressDialog(null);
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada
     *
     * @param message
     */
    public final void showProgressDialog(String message) {

        if (!isStateValid()) {
            return;
        }

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        try {
            if (message != null && message.length() > 0) {
                mProgressDialog = ProgressDialog.show(getActivity(), "", Html.fromHtml(message), true, false);
            } else {
                mProgressDialog = ProgressDialog.show(getActivity(), "", Html.fromHtml(getString(getMessageTextProgressDialog())), true, false);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
        }

        Log.d("PROGRESS_DIAOG_FRAG", "showProgressDialog()");
    }




}
