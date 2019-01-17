package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar.DatePickerFragment;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.ImageUtil;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.ImagensQuartoPagerAdapter;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Moradores;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.bll.Quartos;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.ListGsonSerializer;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class CadastroQuartoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ImageListener {


    @ViewById
    EditText dataEntrada;

    @ViewById
    EditText dataSaida;

    @ViewById
    EditText edtTelefone;

    @ViewById
    EditText edtWhats;

    @ViewById
    EditText edtMorador;

    @Extra
    Imovel imovel;

    @Extra
    Long idImovel;

    @ViewById
    EditText edtEmail;

    @ViewById
    EditText edtPreco;

    @ViewById
    TextView addCheckBox;

    @ViewById
    EditText edtIdentificacaoQuarto;

    @ViewById
    RadioGroup radioOcupadoVago;

    @ViewById
    LinearLayout conteanerCadastroMorador;

    @ViewById
    EditText edtNumero;

    @ViewById
    EditText edtNomeChecked;

    @ViewById
    ImageButton imgAddChecked;

    @ViewById
    FlexboxLayout viewContainerCheckBox;

    @ViewById
    ImageView iconPhoto;
    @ViewById
    CarouselView carouselView;



    List<String> imagens = new ArrayList<>();

    private Long idQuarto;

    private boolean vago = true;
    private static final int CAM_REQUEST = 1;
    private Uri fileUri;

    @SuppressLint("UseSparseArrays")
    HashMap<String, Boolean> checkBoxGroup = new HashMap<>();
    //id dinamico para os checkeds
    int chId = 1000;

    List<Movel> listMoveis = new ArrayList<>();

    @AfterViews
    public void afterViews() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_content_clear);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }


        edtTelefone.addTextChangedListener(MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE));
        edtWhats.addTextChangedListener(MaskEditUtil.insert(edtWhats, MaskEditUtil.MaskType.TELEFONE));
        radioOcupadoVago.setOnCheckedChangeListener(this);
        carouselView.setVisibility(View.GONE);


    }



    void modoEdicao(){

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Descartar informações do quarto?")
                .setMessage("As informações preenchidas até agora serão perdidas.")
                .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CadastroQuartoActivity.super.onBackPressed();
                    }
                }).setNegativeButton("Cancelar", null)
                .show();
    }


    void configureCheckedBox() {
        CheckBox[] ch = new CheckBox[listMoveis.size()];
        for (int i = 0; i < listMoveis.size(); i++) {
            ch[i] = new CheckBox(this);
            ch[i].setId(chId++);
            ch[i].setText(listMoveis.get(i).getNome());
            ch[i].setChecked(listMoveis.get(i).isChecked());
            viewContainerCheckBox.addView(ch[i]);

            if (checkBoxGroup.get(ch[i].getText().toString()) != null) {
                checkBoxGroup.remove(ch[i].getText().toString());
                checkBoxGroup.put(ch[i].getText().toString(), ch[i].isChecked());
            } else {
                checkBoxGroup.put(ch[i].getText().toString(), ch[i].isChecked());
            }

        }


        for (int i = 0; i < listMoveis.size(); i++) {
            ch[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkBoxGroup.get(buttonView.getText().toString()) != null) {
                        checkBoxGroup.remove(buttonView.getText().toString());
                        checkBoxGroup.put(buttonView.getText().toString(), isChecked);
                    } else {
                        checkBoxGroup.put(buttonView.getText().toString(), isChecked);
                    }

                    String stado = isChecked ? "checado" : "não checado";
                    Toast.makeText(CadastroQuartoActivity.this, stado + " " + buttonView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    @Click
    void imgAddChecked() {
        String nomeChecked = edtNomeChecked.getText().toString();

        if (nomeChecked.isEmpty()) {
            return;
        }

        Movel movel = new Movel();
        movel.setNome(edtNomeChecked.getText().toString());

        for (Movel movelList : listMoveis) {
            if (movelList.getNome().equals(movel.getNome())) {
                Toast.makeText(this, "Você já adicionou este movél", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        listMoveis.clear();
        movel.setCheckad(true);
        listMoveis.add(movel);
        edtNomeChecked.setText("");
        configureCheckedBox();

    }


    @Background
    public void salvarQuarto() {
        Quartos quartos = new Quartos();
        Quarto quarto = new Quarto();
        try {
            quarto.setNome(edtIdentificacaoQuarto.getText().toString());
            quarto.setPreco(Double.parseDouble(edtPreco.getText().toString()));
            quarto.setImagens(new ListGsonSerializer().serialize(imagens));
            quarto.setImovelId(idImovel);
            quarto.setQuantidadeCamas(1);
            quarto.setNumero(Integer.parseInt(String.valueOf(edtNumero.getText().toString().isEmpty() ? 0 : edtNumero.getText().toString())));
            quarto.setDescricao(edtIdentificacaoQuarto.getText().toString());
        } catch (Exception e) {
            responseSalvarQuarto("ocorreu um erro", e);;
        }


        try {
            if (quartos.quartoExiste(quarto.getNumero())) {
                responseSalvarQuarto(getResources().getString(R.string.quarto_ja_cadastrado), null);
            } else {

                if (!vago) {
                    quarto.setStatus(1);
                    salvarMorador(quarto);
                } else {
                    quarto.setStatus(0);
                    idQuarto = quartos.salvarQuarto(quarto);
                    responseSalvarQuarto(SqliteConstantes.QUARTO_SALVO_SUCESSO, null);
                }

            }
        } catch (Exception e) {
            responseSalvarQuarto("Erro ao verificar existencia do quarto.", e);
        }


    }

    @UiThread
    void responseSalvarQuarto(String callback, Exception e) {
        dismissProgressDialog();
        if (e != null) {
            DbLogs.Log(SqliteConstantes.ERRO_SALVAR_QUARTO, e, "");
            return;
        }

        if(callback.equals(getResources().getString(R.string.quarto_ja_cadastrado))){
            Toast.makeText(this, callback, Toast.LENGTH_SHORT).show();
            return;
        }
        salvarMovel();
        QuartosActivity_.intent(this).imovelId(idImovel).imovel(imovel).start();

    }


    @Background
    public void salvarMorador(Quarto quarto) {
        Moradores moradores = new Moradores();
        Quartos quartos = new Quartos();
        Morador morador = new Morador();
        try {
            morador.setNome(edtMorador.getText().toString());
            morador.setDataEntrada(dataEntrada.getText().toString());
            morador.setEmail(edtEmail.getText().toString());
            morador.setTelefone(MaskEditUtil.unmask(edtTelefone.getText().toString()));
            morador.setWhats(MaskEditUtil.unmask(edtWhats.getText().toString()));
            morador.setDataSaida(dataSaida.getText().toString());
        } catch (Exception e) {
            responseMoradorUiThread("um erro ocorreu",e);
        }


        try {
            if (moradores.moradorExiste(morador.getTelefone())) {
                responseMoradorUiThread(SqliteConstantes.MORADOR_JA_CADASTRADO, null);
            } else {
                idQuarto = quartos.salvarQuarto(quarto);
                morador.setQuartoId(idQuarto);
                responseSalvarQuarto(SqliteConstantes.QUARTO_SALVO_SUCESSO, null);
                responseMoradorUiThread(getResources().getString(R.string.morador_ja_cadastrado), null);
                moradores.salvarMorador(morador);

            }
        } catch (Exception e) {
            responseMoradorUiThread("Erro: ", e);

        }

    }

    @UiThread
    void responseMoradorUiThread(String callback, Exception e) {
        dismissProgressDialog();
        if (e != null) {
            DbLogs.Log(SqliteConstantes.ERRO_VERIFICAR_EXISTENCIA_MORADOR, e, "");
            return;
        }

        if (callback.equals(getResources().getString(R.string.morador_ja_cadastrado))){
            Toast.makeText(this, callback, Toast.LENGTH_SHORT).show();
            return;
        }

        salvarMovel();
        VisaoGeral_.intent(this).start();

    }

    @Background
    void salvarMovel() {
        Moveis moveis = new Moveis();
        for (String checkedBox : checkBoxGroup.keySet()) {
            boolean value = checkBoxGroup.get(checkedBox);
            Movel movel = new Movel();
            movel.setNome(checkedBox);
            movel.setCheckad(value);
            if (idQuarto != null) {
                movel.setQuartoId(idQuarto);
            }

            try {
                if (!moveis.movelExiste(movel.getNome())) {
                    moveis.salvarMovel(movel);
                    responseMovelUiThread("Movel salvo", null);
                } else {
                    moveis.atualizarMovel(movel);
                    responseMovelUiThread("Movel atualizado", null);
                }
            } catch (Exception e) {
                responseMovelUiThread("Erro ao verificar existencia do movel", e);
            }

        }
    }

    @UiThread
    void responseMovelUiThread(String callback, Exception e) {
        dismissProgressDialog();
        if (e != null) {
            DbLogs.Log(SqliteConstantes.ERRO_VERIFICAR_EXISTENCIA_MOVEL, e, "");
            return;
        }
        Toast.makeText(this, callback, Toast.LENGTH_SHORT).show();

    }

    @Background
    void validarMorador() {
        Moradores moradores = new Moradores();
        try {
            boolean existe = moradores.moradorExiste(edtWhats.getText().toString());
            validarMoradorRespostaUiThread(null, existe);
        } catch (Exception e) {
            validarMoradorRespostaUiThread(e, false);
        }

    }

    @UiThread
    void validarMoradorRespostaUiThread(Exception e, boolean existe) {
        if (e != null) {
            DbLogs.Log(SqliteConstantes.ERRO_VERIFICAR_EXISTENCIA_MORADOR, e, "");
            return;
        }

        if (!existe) {
            showProgressDialog("Salvando o quarto...");
            salvarQuarto();
        } else {
            Toast.makeText(this, "Morador já cadastrado em outro quarto.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbOcupado) {
            conteanerCadastroMorador.setVisibility(View.VISIBLE);
            vago = false;
        } else if (checkedId == R.id.rbVago) {
            vago = true;
            conteanerCadastroMorador.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }


        if (item.getItemId() == R.id.action_salvar) {
            if (validation()) {
                if (listMoveis.size() == 0) {
                    new AlertDialog.Builder(this)
                            .setTitle("Você não adicionou nenhum móvel no quarto.")
                            .setMessage("Tem certeza que deseja continuar?")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (validation()) {
                                        showProgressDialog("Salvando o quarto...");
                                        salvarQuarto();
                                    }

                                }
                            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                } else {
                    if (validation()) {
                        showProgressDialog("Salvando o quarto...");
                        salvarQuarto();
                    }
                }
            }
        }
        return true;
    }


    public void setEditTextDatePicker(final EditText text) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
        newFragment.setOnDateSelectedListener(new DatePickerFragment.OnDateSelectedListener() {
            @Override
            public void onDateSelected(DatePicker view, String formattedDate) {
                text.setText(formattedDate);
            }
        });
    }

    @Click
    void dataSaida() {
        setEditTextDatePicker(dataSaida);
    }

    @Click
    void dataEntrada() {
        setEditTextDatePicker(dataEntrada);
    }

    public boolean validation() {

        boolean valid = true;

        if (!vago) {

            if (TextUtils.isEmpty(dataEntrada.getText())) {
                dataEntrada.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }

            if (TextUtils.isEmpty(dataSaida.getText())) {
                dataSaida.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }


            if (TextUtils.isEmpty(edtTelefone.getText())) {
                edtTelefone.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }

            if (TextUtils.isEmpty(edtWhats.getText())) {
                edtWhats.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }

            if (TextUtils.isEmpty(edtIdentificacaoQuarto.getText())) {
                edtIdentificacaoQuarto.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }

            if (TextUtils.isEmpty(edtNumero.getText())) {
                edtNumero.setError(getString(R.string.campo_obrigatorio));
                valid = false;
            }
        }


        if (TextUtils.isEmpty(edtIdentificacaoQuarto.getText())) {
            edtIdentificacaoQuarto.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtNumero.getText())) {
            edtNumero.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtPreco.getText())) {
            edtPreco.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }


        return valid;
    }


    void tirarPhoto() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        // cria um Uri com o caminho do arquivo para guardar a foto
        //ex: /storage/sdcard0/Android/data/nome.da.sua.package/files/Pictures/MyCameraApp/IMG_20160817_000919.jpg
        fileUri = ImageUtil.getOutputPictureUri("Fotos do quarto");

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQUEST) {
            if (resultCode == RESULT_OK) {


                //salvar o caminho no banco

                //atualizar o array de string
                imagens.add("file:///" + fileUri.getPath());

                carouselView.setVisibility(View.VISIBLE);
                carouselView.setImageListener(this);
                carouselView.setPageCount(imagens.size() == 0 ? 1 : imagens.size());


                //atualizar o view pager
                // imagensQuartoPagerAdapter.atualizarImagens("file:///" + fileUri.getPath());

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getBaseContext(), "Captura da foto cancelada", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getBaseContext(), "Captura da foto falhou", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }




    @Click
    void iconPhoto() {
        tirarPhoto();
    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.get().load(imagens.get(position)).into(imageView);
    }
}
