package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.application.ThemeApplication;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.androidannotations.annotations.EFragment;

/**
 * Este Fragment possui funcionalidades específicas, como Loading e outras
 */
@SuppressWarnings("ConstantConditions")
@EFragment
public abstract class FragmentW extends BaseFragment {

    private View viewLoading;
    private ViewGroup viewContent;
    private TextView textViewMessage;
    private ProgressWheel mProgressWheel;
    private ProgressDialog mProgressDialog;
    protected String TAG = this.getClass().getName();

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);


        if (!isStateValid()) {
            return;
        }

        /*
         * FUNÇÃO LOADING
         *
         * Esta função adiciona a View principal do Fragment uma View de Loading.
         * Assim, quando houver um carregamento de dados que não é instantâneo,
         * o utilizador pode chamar a função setLoading passando true e a View
         * de loading será exibida. Passando false, ela será escondida.
         *
         * REGRAS:
         *
         * 1) O pai da View principal do Fragment deve ser um ViewGroup (Linear, Relative, TableLayout, etc).
         *    Se o pai do layout não for um ViewGroup, não irá funcionar
         *    Se for um TextView na raiz, por exemplo, não irá funcionar.
         *
         * 2) O pai deve ter ID igual a "viewContent"
         *    A View que tiver esse ID na View principal do Fragment é a que será escondida em modo Loading true!
         *    Se não existir essa view no Fragment, o recurso não irá funcionar.
         *
         * 3) Caso não exista um ID no pai como "viewContent", ele poderá ser informado no filho
         *    , mas se não for informado e o dev chamar o setLoading, uma exceção em tempo de execução
         *    será propagada.
         */
        initLoadingResource(view);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        //garante que progres dialog velho não fique ativo
        dismissProgressDialog();
        super.onDestroy();
    }


    public Fragment findFragmentByTag(String tag) {
        return getChildFragmentManager().findFragmentByTag(tag);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        replaceFragment(fragment, R.id.container, tag);
    }

    public void replaceFragment(Fragment fragment, int id, String tag) {
        getChildFragmentManager().beginTransaction()
                .replace(id, fragment, tag)
                .commit();
    }

    /**
     * Tenta iniciar o recurso de loading de acordo com a View informada; que deve ser uma instância de ViewGroup
     *
     * @param view
     */
    @SuppressLint("InflateParams")
    private void initLoadingResource(@NonNull View view) {

        if (view instanceof ViewGroup) {

            ViewGroup viewGroup = (ViewGroup) view;

            if (viewContent == null) {
                //Primeiro tenta carregar o content, pois,
                //se ele nao existir, o recurso nao funcionará
                viewContent = viewGroup.findViewById(R.id.viewContent);
            }

            if (viewContent != null) {

                //talvez esse layout use um customizado, então vamos dar prioridade a ele
                viewLoading = viewGroup.findViewById(R.id.viewLoading);
                if (viewLoading == null) {
                    viewLoading = LayoutInflater.from(getActivity()).inflate(R.layout.view_loading, null);
                    viewGroup.addView(viewLoading);
                }

                //ainda sim, tenta encontrar esses componentes
                mProgressWheel = (ProgressWheel) viewLoading.findViewById(R.id.progressBar);
                if (mProgressWheel != null) {
                    mProgressWheel.setBarColor(ThemeApplication.getColor(getActivity(), R.attr.colorAccent));
                }
                textViewMessage = (TextView) viewLoading.findViewById(R.id.textViewMessage);
                if (textViewMessage != null) {
                    textViewMessage.setText(getMessageTextLoading());
                }

                //Define o estado inicial (nao mostrar a view Loading)
                viewLoading.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Esta função alterna o estado de Loading mediante ao parâmetro informado.
     * Quando TRUE, a View principal será escondida e a View de loading será exibida.
     * Quando FALSE, a View principal será exibida e a View de loading será escondida.
     *
     * @param isLoading
     * @param modeInvisible
     */

    public final void setLoading(boolean isLoading, boolean modeInvisible) {

        //ainda tenta carregar a loading view, caso esteja nulo
        if (viewContent == null) {
            initLoadingResource(getView());
        }

        Log.i(TAG, "setLoading = " + isLoading);

        View viewHide = isLoading ? viewContent : viewLoading;
        View viewShow = isLoading ? viewLoading : viewContent;

        if (viewHide != null && viewShow != null) {
            if (viewShow.getVisibility() == View.VISIBLE) {
                return;
            }
            viewHide.setVisibility((modeInvisible && viewHide == viewContent) ? View.INVISIBLE : View.GONE);
            viewShow.setVisibility(View.VISIBLE);
        } else {
        }
    }

    /**
     * Se o loading em modo padrão
     *
     * @param isLoading
     */
    public final void setLoading(boolean isLoading) {
        setLoading(isLoading, false);
    }


    /**
     * Diz se estamos no modo de loading
     *
     * @return
     */
    public final boolean isLoading() {

        return viewLoading != null && viewLoading.getVisibility() == View.VISIBLE;
    }

    public final void setLoadingColor(int color) {
        if (mProgressWheel != null) {
            mProgressWheel.setBarColor(color);
        }
    }

    @Nullable
    public final View getViewLoading() {
        return viewLoading;
    }

    @Nullable
    public final View getViewContent() {
        return viewContent;
    }

    /**
     * Quando chamada, faz atualizar o texto de loading
     */
    public final void notifyChangeMessageTextLoading() {
        if (textViewMessage != null) {
            textViewMessage.setText(getMessageTextLoading());
        }
    }

    @StringRes
    public final int getMessageTextLoading() {
        return R.string.aguarde;
    }


    public final void setViewContent(ViewGroup viewContent) {
        this.viewContent = viewContent;

        initLoadingResource(getView());
    }


    /**
     * Insere elevação padrão em uma View
     *
     * @param view
     */
    public final void elevationView(@NonNull View view) {

        elevationView(view, 8);
    }

    /**
     * Insere elevação informada em uma View
     *
     * @param view
     */
    public final void elevationView(@NonNull View view, float elevation) {

        ViewCompat.setElevation(view, elevation);
    }

    /**
     * Função que tem a finalidade de avisar o fragment sobre uma nova intent na Activity
     * Utilidade principal para pesquisa por voz
     *
     * @param intent
     */
    public void onNewIntent(Intent intent) {

    }

    @StringRes
    public int getMessageTextProgressDialog() {
        return R.string.aguarde;
    }


    /**
     * Quando chamada, faz atualizar o texto do progress dialog
     */
    public final void notifyChangeMessageTextProgressDialog() {

        if (!isStateValid()) {
            return;
        }

        if (mProgressDialog != null) {
            mProgressDialog.setMessage(getString(getMessageTextProgressDialog()));
        }
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

    public final void updateProgressMessage(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
        }
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada
     */
    public final void showProgressDialog() {
        showProgressDialog(null);
    }


    public final void setProgressDialogMax(int max) {

        if (mProgressDialog != null) {
            mProgressDialog.setMax(max);
        }
    }

    public final void setProgressDialogProgress(int progress) {
        if (mProgressDialog != null) {
            mProgressDialog.setProgress(progress);
        }
    }

    /**
     * Abre o dialog com mensagem padrão ou customizada  2568-3523  ou 75023819
     */
    public final void showProgressDialog(@StringRes int messageResid) {
        showProgressDialog(getString(messageResid));
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

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).dismissProgressDialog();
        }
    }

    public final void showProgressDialogActivity(String message) {

        if (!isStateValid()) {
            return;
        }

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgressDialog(message);
        }
    }


    public final void showProgressDialogActivity() {

        if (!isStateValid()) {
            return;
        }

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    public final void invalidateOptionsMenu() {

        if (isStateValid() && !ActivityCompat.invalidateOptionsMenu(getActivity())) {
            getActivity().supportInvalidateOptionsMenu();
        }
    }


    /**
     * Considerando que a ViewLoading nao é um aView utuil, o objetivo dessa função
     * é retornar aquela View que contem as Views utilizadas na navegação
     *
     * @return
     */
    @Nullable
    public ViewGroup getRootViewUtil() {
        return viewContent != null ? viewContent : (ViewGroup) getView();
    }


}
