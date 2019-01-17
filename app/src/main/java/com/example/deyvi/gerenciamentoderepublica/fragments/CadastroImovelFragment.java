package com.example.deyvi.gerenciamentoderepublica.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.EnderecoUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.ImageUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.InternetUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroImovelActivity;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Enderecos;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseStepCadastroLocatarioFragment;
import com.google.android.gms.common.images.internal.ImageUtils;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.deyvi.gerenciamentoderepublica.Util.validacion.ImageUtil.decodeSampledBitmapFromFile;

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
    @ViewById
    ImageView imgPhoto;
    @ViewById
    ImageView iconPhoto;
    @ViewById
    RobotoTextView txtTitulo;


    private static final int CAM_REQUEST = 1;
    private Uri fileUri = null;

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


    @Click(R.id.imageButtonSearchCEP)
    void onSearchCEPClick() {
        if (edtCep.getText().toString().isEmpty()) {
            edtCep.setError(getString(R.string.informe_cep));
            return;
        }
        if (!InternetUtil.isConected(getBaseActivity())) {
            Toast.makeText(getBaseActivity(), "Verifique sua conexão...", Toast.LENGTH_SHORT).show();
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
        dismissProgressDialog();
        if (enderecoImovel == null) {
            Toast.makeText(getBaseActivity(), "Cep não existe.", Toast.LENGTH_SHORT).show();
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
            salvarEndereco(enderecoImovel);
        }
    }

    @Background
    void salvarEndereco(Endereco endereco) {
        Enderecos enderecos = new Enderecos();
        //Salva endereco
        if (!enderecos.enderecoExiste(endereco.getCep(), endereco.getNumero())) {
            enderecoId = enderecos.salvarEndereco(endereco);
            responseEndereco();
        }
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
        concluir();
    }

    public void concluir() {
        if (validation()) {
            Imovel imovel = new Imovel();

            if (!edtValorAluguel.getText().toString().isEmpty()) {
                imovel.setValor(Double.parseDouble(edtValorAluguel.getText().toString()));
            }

            imovel.setEnderecoId(enderecoId);
            if(fileUri != null){
                imovel.setImagem(ImageUtil.decodeImageString(fileUri));
                imovel.setCaminhoImagem(fileUri.getPath());
            }

            imovel.setNome(edtNomeImovel.getText().toString());
            imovel.setQuantQuartos(Integer.parseInt(edtNumeroDeQuartos.getText().toString()));
            imovel.setAlugado(rdAlugado.isChecked());
            salvarCadastroBackground(imovel);
            showProgressDialog("Salvando seus dados...");
        }
    }


    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
    }


    @Background
    void salvarCadastroBackground(Imovel imovel) {
        Imoveis imoveis = new com.example.deyvi.gerenciamentoderepublica.bll.Imoveis();
        try {
            if (imoveis.imovelExiste(imovel.getNome())) {
                naoAutorizado();
            } else {
                imoveis.salvarImovel(imovel);
                salvarCadastroClienteFinished(imovel, null);
            }
        } catch (Exception e) {
            salvarCadastroClienteFinished(imovel, e);
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
        if (ex != null) {
            DbLogs.Log("Algum erro ocorreu", ex, "");
        } else {
            ((CadastroActivity) getActivity()).onCompleted(null);
        }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST) {
            if (resultCode == RESULT_OK) {

                //Lê a foto gravada, redimensiona para 300x300 e coloca-a
                // na ImageView
                imgPhoto.setImageBitmap(decodeSampledBitmapFromFile(fileUri));

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getBaseActivity(), "Captura da foto cancelada", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getBaseActivity(), "Captura da foto falhou", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }


    @Click
    void iconPhoto() {
        tirarPhoto();
    }

    @Click
    void imgPhoto() {
        tirarPhoto();
    }


    void tirarPhoto() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // cria um Uri com o caminho do arquivo para guardar a foto
        //ex: /storage/sdcard0/Android/data/nome.da.sua.package/files/Pictures/MyCameraApp/IMG_20160817_000919.jpg
        fileUri = ImageUtil.getOutputPictureUri("Fotos do Imovel");

        if (fileUri != null) {

            // Cria o intent para chamar a câmara
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // seta o caminho do arquivo para guardar a foto
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // inicia a captura da foto
            startActivityForResult(intent, CAM_REQUEST);
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(CadastroImovelActivity.DataCommunication event) {
        concluir();
    }
}
