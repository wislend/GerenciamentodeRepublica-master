package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar.DatePickerFragment;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.bll.Moradores;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.bll.Quartos;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.flexbox.FlexboxLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class CadastroQuartoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @ViewById
    EditText dataEntrada;

    @ViewById
    EditText dataSaida;

    @ViewById
    RelativeLayout campoDataEntrada;

    @ViewById
    RelativeLayout campoDataSaida;

    @ViewById
    EditText edtTelefone;

    @ViewById
    EditText edtWhats;

    @ViewById
    EditText edtMorador;

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
    android.support.v7.widget.Toolbar toolbar;

    @ViewById
    FlexboxLayout viewContainerCheckBox;


    private Quarto quarto;

    //bll 
    private Quartos quartos;

    //bll
    private Moveis moveis;

    private Long idQuarto;

    private Moradores moradores;

    private boolean vago = true;

    @SuppressLint("UseSparseArrays")
    HashMap<String, Boolean> checkBoxGroup = new HashMap<>();
    //id dinamico para os checkeds
    int chId = 1000;

    List<Movel> listMoveis = new ArrayList<>();

    @AfterViews
    public void afterViews() {
        setSupportActionBar(toolbar);
        //MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE);
        // MaskEditUtil.insert(edtWhats, MaskEditUtil.MaskType.TELEFONE);
        moveis = new Moveis();
        if (moveis.todosMoveis() != null) {
            listMoveis.addAll(moveis.todosMoveis());
        }

        test();
        radioOcupadoVago.setOnCheckedChangeListener(this);
        if (listMoveis != null) {
            configureCheckedBox();
        }
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


    @Click
    void imgAddChecked() {
        String nomeChecked = edtNomeChecked.getText().toString();

        if (nomeChecked.isEmpty()) {
            return;
        }

        Movel movel = new Movel();
        movel.setNome(edtNomeChecked.getText().toString());

        if (listMoveis.contains(movel)) {
            Toast.makeText(this, "Você já adicionou este movél", Toast.LENGTH_SHORT).show();
        } else {
            listMoveis.clear();
            movel.setCheckad(true);
            listMoveis.add(movel);
            edtNomeChecked.setText("");
            configureCheckedBox();
        }
    }



    public void salvarQuarto() {
        quartos = new Quartos();
        quarto = new Quarto();
        quarto.setNome(edtIdentificacaoQuarto.getText().toString());
        quarto.setPreco(100.00);
        quarto.setStatus(0);
        quarto.setQuantidadeCamas(1);
        quarto.setNumero(Integer.parseInt(String.valueOf(edtNumero.getText().toString().isEmpty() ? 0 : edtNumero.getText().toString())));
        quarto.setDescricao("quarto grande.");

        if (quartos.quartoExiste(quarto.getNumero())) {
            Toast.makeText(this, "O quarto " + quarto.getNome() + " já foi cadastrado.", Toast.LENGTH_SHORT).show();
            VisaoGeral_.intent(this).start();
        } else {
            idQuarto = quartos.salvarQuarto(quarto);
            Toast.makeText(this, SqliteConstantes.QUARTO_SALVO_SUCESSO, Toast.LENGTH_SHORT).show();
            if (!vago) {
                salvarMorador();
            }
        }
    }


    public void salvarMorador() {
        moradores = new Moradores();
        Morador morador = new Morador();
        morador.setNome(edtMorador.getText().toString());
        morador.setDataEntrada(dataEntrada.getText().toString());
        morador.setEmail(edtEmail.getText().toString());
        morador.setTelefone(edtTelefone.getText().toString());
        morador.setWhats(edtWhats.getText().toString());
        morador.setDataSaida(dataSaida.getText().toString());
        morador.setQuartoId(idQuarto);
        if (moradores.moradorExiste(morador.getTelefone())) {
            Toast.makeText(this, SqliteConstantes.MORADOR_JA_CADASTRADO, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Morador salvo com sucesso", Toast.LENGTH_SHORT).show();
            moradores.salvarMorador(morador);
            salvarMovel();
            VisaoGeral_.intent(this).start();
        }
    }


    void salvarMovel() {
        for (String checkedBox : checkBoxGroup.keySet()) {
            boolean value = checkBoxGroup.get(checkedBox);
            Movel movel = new Movel();
            movel.setNome(checkedBox);
            movel.setCheckad(value);
            if (idQuarto != null) {
                movel.setQuartoId(idQuarto);
            }
            if (!moveis.movelExiste(movel.getNome())) {
                moveis.salvarMovel(movel);
                Toast.makeText(this, "Movel salvo", Toast.LENGTH_SHORT).show();
            } else {
                moveis.atualizarMovel(movel);
                Toast.makeText(this, "Movel atualizado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void test() {
        edtMorador.setText("João da Silva");
        edtEmail.setText("wislen.souza@aedu.com");
        edtTelefone.setText("988252650");
        edtWhats.setText("988252650");
        edtPreco.setText("500,00");
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
    protected void onDestroy() {
        salvarMovel();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
            DetalhesQuartoActivity_.intent(this).start();
            if (!vago) {
                moradores = new Moradores();
                if (!moradores.moradorExiste(edtWhats.getText().toString())) {
                    salvarQuarto();

                } else {
                    Toast.makeText(this, "Morador já cadastrado em outro quarto.", Toast.LENGTH_SHORT).show();
                }
            }else {
                salvarQuarto();
                salvarMovel();
            }
        }
        return true;
    }



    public void setEditTextDatePicker(final EditText text) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSelectedListener(new DatePickerFragment.OnDateSelectedListener() {
            @Override
            public void onDateSelected(DatePicker view, String formattedDate) {
                text.setText(formattedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "date picker");
    }

    @Click
    void dataSaida() {
        setEditTextDatePicker(dataSaida);
    }


    @Click
    void campoDataSaida() {
        setEditTextDatePicker(dataSaida);
    }


    @Click
    void campoDataEntrada() {
        setEditTextDatePicker(dataEntrada);
    }

    @Click
    void dataEntrada() {

        setEditTextDatePicker(dataEntrada);
    }


}
