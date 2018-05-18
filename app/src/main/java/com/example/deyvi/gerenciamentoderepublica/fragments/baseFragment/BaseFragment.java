package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class BaseFragment extends Fragment  implements Step {

    private boolean mRequired = false;
    private ProgressDialog mProgressDialog;
    protected String TAG = this.getClass().getName();


    @Nullable
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
