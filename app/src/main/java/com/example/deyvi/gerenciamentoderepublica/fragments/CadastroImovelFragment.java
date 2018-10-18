package com.example.deyvi.gerenciamentoderepublica.fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.EnderecoUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.bll.Enderecos;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
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
        RadioGroup.OnCheckedChangeListener {


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
    EditText edtEstado;
    @ViewById
    EditText edtJurosDia;
    @ViewById
    EditText edtJurosMes;

    //endregion
    private List<String> estadosList = new ArrayList<>();

    private Long enderecoId;

    @AfterViews
    void initCadastroImovelFrag() {
        inserirMask();
        setJurosPadrao();
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
       // setSpinText(edtEstado,getString(R.string.opcao_nenhum));
        String[] estados = getResources().getStringArray(R.array.estados_brasileiros);
        estadosList.addAll(Arrays.asList(estados));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseActivity(),
                R.array.estados_brasileiros, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // edtEstado.setAdapter(adapter);
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
    void finishAddressUI(Endereco enderecoImovel) {

        //se o resultado for nulo ou o usuário não ter esperado e cancelado o diálogo
        if (enderecoImovel == null || !isProgressDialogShowing()) {
            return;
        }
        edtRua.setText("");
        edtNumero.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        edtEstado.setText("");

        edtRua.setText(enderecoImovel.getRua());
        edtNumero.setText(enderecoImovel.getNumero() == 0 ? "" : String.valueOf(enderecoImovel.getNumero()));
        edtBairro.setText(enderecoImovel.getBairro());
        edtCidade.setText(enderecoImovel.getCidade());
        edtEstado.setText(enderecoImovel.getEstado());

        if (enderecoImovel.getCep() != null) {
            salvarEndereco(enderecoImovel);
        }
    }

    @Background
    void salvarEndereco(Endereco endereco) {
        Enderecos enderecos = new Enderecos();
        //Salva endereco
        enderecoId = enderecos.salvarEndereco(endereco);
        responseEndereco();
    }

    @UiThread
    void responseEndereco() {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Endereço Salvo", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }


    @Override
    @UiThread
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        if (validation()){
        Imovel imovel = new Imovel();

        if(!edtValorAluguel.getText().toString().isEmpty()){
            imovel.setValor(Double.parseDouble(edtValorAluguel.getText().toString()));
        }

        imovel.setEnderecoId(enderecoId);
        imovel.setNome(edtNomeImovel.getText().toString());
        imovel.setQuantQuartos(Integer.parseInt(edtNumeroDeQuartos.getText().toString()));
        imovel.setAlugado(rdAlugado.isChecked());
        salvarCadastroBackground(imovel);
        showProgressDialog("Salvando seus dados...");}
    }


    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
    }


    @Background
    void salvarCadastroBackground(Imovel imovel) {
        Imoveis imoveis = new com.example.deyvi.gerenciamentoderepublica.bll.Imoveis();
        if (imoveis.imovelExiste(imovel.getNome())){
            naoAutorizado();
        }else {
            imoveis.salvarImovel(imovel);
            salvarCadastroClienteFinished(imovel, null);
        }

    }


    @UiThread
    void naoAutorizado() {
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
        if (checkedId == R.id.rdPropio) {
            edtValorAluguel.setVisibility(View.GONE);
        } else {
            edtValorAluguel.setVisibility(View.VISIBLE);
        }
    }
}
