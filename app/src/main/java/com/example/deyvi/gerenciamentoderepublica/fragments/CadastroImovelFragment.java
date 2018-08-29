package com.example.deyvi.gerenciamentoderepublica.fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.EnderecoUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.bll.Enderecos;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.dal.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseStepCadastroLocatarioFragment;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EFragment(R.layout.cadastro_imovel_fragment)
public class CadastroImovelFragment extends BaseStepCadastroLocatarioFragment implements BlockingStep,
        RadioGroup.OnCheckedChangeListener{


    //region ById
    @ViewById
    RadioButton rdPropio;
    @ViewById
    RadioButton rdAlugado;
    @ViewById
    RadioGroup radioPropioAlugado;
    @ViewById
    EditText edtNomeImovel;
    @ViewById
    EditText edtCep;
    @ViewById
    EditText edtNumeroDeQuartos;
    @ViewById
    EditText edtValorAluguel;
    @ViewById
    ImageView imageButtonSearchCEP;
    @ViewById
    EditText edtRua;
    @ViewById
    EditText edtNumero;
    @ViewById
    EditText edtPontoReferencia;
    @ViewById
    EditText edtBairro;
    @ViewById
    EditText edtCidade;
    @ViewById
    Spinner spinnerEstado;
    @ViewById
    EditText edtJurosDia;
    @ViewById
    EditText edtJurosMes;

    //endregion
    private List<String> estadosList = new ArrayList<>();
    private Enderecos enderecos;
    private com.example.deyvi.gerenciamentoderepublica.bll.Imoveis imoveis;
    private Long enderecoId;

    @AfterViews
    void initCadastroImovelFrag() {
        inserirMask();
        setJurosPadrao();
        setCidadesSpinner();
        radioPropioAlugado.setOnCheckedChangeListener(this);

    }


    void inserirMask() {
        edtCep.addTextChangedListener(MaskEditUtil.insert(edtCep, MaskEditUtil.MaskType
                .CEP));
    }

    public boolean validation() {

        boolean valid = true;

        if (TextUtils.isEmpty(edtNomeImovel.getText())) {
            edtNomeImovel.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtRua.getText())) {
            edtRua.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }


        if (TextUtils.isEmpty(edtBairro.getText())) {
            edtBairro.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtNumero.getText())) {
            edtNumero.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtCidade.getText())) {
            edtCidade.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtNomeImovel.getText())) {
            edtNomeImovel.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }
        return valid;
    }

    void setJurosPadrao() {
        edtJurosDia.setText("2%");
        edtJurosMes.setText("10%");
    }

    void setCidadesSpinner() {
        //setSpinText(spinnerEstado,getString(R.string.opcao_nenhum));
        String[] estados = getResources().getStringArray(R.array.estados_brasileiros);
        estadosList.addAll(Arrays.asList(estados));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseActivity(),
                R.array.estados_brasileiros, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);
    }


    @Click(R.id.imageButtonSearchCEP)
    void onSearchCEPClick() {
        if (edtCep.getText().toString().isEmpty()) {
            edtCep.setError(getString(R.string.informe_cep));
            return;
        }
        showProgressDialog();
        findAddressBackground(MaskEditUtil.unmask(edtCep.getText().toString()));
    }


    @Background
    void findAddressBackground(String cep) {
        finishAddressUI(EnderecoUtil.discover(cep));
    }


    @UiThread
    void finishAddressUI(com.example.deyvi.gerenciamentoderepublica.entitys.Endereco enderecoImovel) {

        //se o resultado for nulo ou o usuário não ter esperado e cancelado o diálogo
        if (enderecoImovel == null || !isProgressDialogShowing()) {
            dismissProgressDialog();
            return;
        }
        dismissProgressDialog();
        //inicia bll de endereço
        enderecos = new Enderecos();
        edtRua.setText(enderecoImovel.getRua());
        edtNumero.setText(enderecoImovel.getNumero() == 0 ? "" : String.valueOf(enderecoImovel.getNumero()));
        edtBairro.setText(enderecoImovel.getBairro());
        edtCidade.setText(enderecoImovel.getCidade());
        //Salva endereco
        enderecoId = enderecos.salvarEndereco(enderecoImovel);

    }

    public void setSpinText(Spinner spin, String text) {
        if (spinnerEstado == null) {

        }
        for (int i = 0; i < spin.getAdapter().getCount(); i++) {
            if (spin.getAdapter().getItem(i).toString().contains(text)) {
                spin.setSelection(i);
            }
        }
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        showProgressDialog("Validando informações...");
        salvarCadastroBackground();
    }



    @Override
    @UiThread
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        showProgressDialog("Salvando seu cadastro...");
        salvarCadastroBackground();
    }



    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
    }





    @Background
    void salvarCadastroBackground() {
        //inicia bll de imovel
        imoveis = new com.example.deyvi.gerenciamentoderepublica.bll.Imoveis();
        boolean enderecoExiste = enderecos.enderecoExiste(edtCep.getText().toString());

       // if (!enderecoExiste){
            Imovel imovel = new Imovel();
            imovel.setEnderecoId(enderecoId);
            imovel.setNome(edtNomeImovel.getText().toString());
            imovel.setAlugado(true);
            imovel.setQuantQuartos(Integer.parseInt(edtNumeroDeQuartos.getText().toString()));
            imovel.setJurosDia(1200);
            imovel.setJurosMes(1200);
            imoveis.salvarImovel(imovel);
            salvarCadastroClienteFinished(imovel, null);
      //  }else {
      //      naoAutorizado();
      //  }

    }


    @UiThread
    void naoAutorizado(){
        dismissProgressDialog();
        Toast.makeText(getContext(), SqliteConstantes.IMOVEL_JA_CADASTRADO, Toast.LENGTH_SHORT).show();
    }

    @UiThread(delay = 3000)
    void salvarCadastroClienteFinished(Imovel imovel, Exception ex) {
        dismissProgressDialog();
        ((CadastroActivity) getActivity()).onCompleted(null);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //check se o campo de valor do aluguel do imovel aparecerá
            if (checkedId == R.id.rdPropio){
                edtValorAluguel.setVisibility(View.GONE);
            }else{
                edtValorAluguel.setVisibility(View.VISIBLE);
            }
    }
}
