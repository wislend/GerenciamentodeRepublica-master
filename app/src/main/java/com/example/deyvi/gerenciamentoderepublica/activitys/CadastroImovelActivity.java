package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Enderecos;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_imovel)
public class CadastroImovelActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


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
    @ViewById
    Toolbar toolbar;

    private Endereco endereco;

    @AfterViews
    void initCadastroImovelFrag() {


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
        if (enderecoImovel == null) {
            return;
        }
        edtRua.setText("");
        edtNumero.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        edtEstado.setText("");

        edtRua.setText(enderecoImovel.getRua());
        edtNumero.setText(enderecoImovel.getNumero());
        edtBairro.setText(enderecoImovel.getBairro());
        edtCidade.setText(enderecoImovel.getCidade());
        edtEstado.setText(enderecoImovel.getEstado());

        if (enderecoImovel.getCep() != null) {
            dismissProgressDialog();
            endereco = enderecoImovel;
        }
    }

    @Background
    void salvarEndereco(Endereco endereco) {
        Enderecos enderecos = new Enderecos();
        //Salva endereco
        endereco.setNumero(edtNumero.getText().toString());
        enderecos.salvarEndereco(endereco);
        responseEndereco();
    }

    @UiThread
    void responseEndereco() {
        Toast.makeText(this, "Endereço Salvo", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
            if (validation()) {
                Imovel imovel = new Imovel();

                if (!edtValorAluguel.getText().toString().isEmpty()) {
                    imovel.setValor(Double.parseDouble(edtValorAluguel.getText().toString()));
                }

                imovel.setNome(edtNomeImovel.getText().toString());
                imovel.setQuantQuartos(Integer.parseInt(edtNumeroDeQuartos.getText().toString()));
                imovel.setAlugado(rdAlugado.isChecked());
                showProgressDialog("Salvando seus dados...");
                salvarCadastroBackground(imovel);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Background
    void salvarCadastroBackground(Imovel imovel) {
        Imoveis imoveis = new com.example.deyvi.gerenciamentoderepublica.bll.Imoveis();
        try {
            if (imoveis.imovelExiste(imovel.getNome())) {
                naoAutorizado();
            } else {
                salvarEndereco(endereco);
                imovel.setEnderecoId(endereco.getId());
                //evita que imovel seja salvo antes que exista o id do endereco.
                if (imovel.getEnderecoId() != null){
                    imoveis.salvarImovel(imovel);
                }
                salvarCadastroClienteFinished(imovel, null);
            }
        } catch (Exception e) {
            salvarCadastroClienteFinished(imovel, e);
        }


    }

    private void salvarCadastroClienteFinished(Imovel imovel, Exception e) {
        if (imovel.getEnderecoId() == null){
            dismissProgressDialog();
            return;
        }

        if (e != null) {
            DbLogs.Log("Erro ao salvar cadastro cliente", e, "");
            return;
        }

        dismissProgressDialog();
        VisaoGeral_.intent(this).start();
        finish();

    }


    @UiThread
    void naoAutorizado() {
        dismissProgressDialog();
        Toast.makeText(this, SqliteConstantes.IMOVEL_JA_CADASTRADO, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
