package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar.DatePickerFragment;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class CadastroQuartoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewById
    MaterialEditText dataEntrada;

    @ViewById
    MaterialEditText dataSaida;

    @ViewById
    RelativeLayout campoDataEntrada;

    @ViewById
    RelativeLayout campoDataSaida;

    @ViewById
    MaterialEditText edtTelefone;

    @ViewById
    MaterialEditText edtWhats;

    @ViewById
    MaterialEditText edtMorador;

    @ViewById
    MaterialEditText edtEmail;


    @ViewById
    MaterialEditText edtPreco;

    @ViewById
    MaterialEditText edtIdentificacaoQuarto;

    @ViewById
    RadioGroup idRadioGroup;

    @ViewById
    LinearLayout conteanerCadastroMorador;

    @ViewById
    MaterialEditText edtNumero;

    private Morador morador;

    private  Quarto quarto;

    private boolean vago = true;

    @AfterViews
    public void afterViews() {

        //MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE);
        // MaskEditUtil.insert(edtWhats, MaskEditUtil.MaskType.TELEFONE);
        test();
        idRadioGroup.setOnCheckedChangeListener(this);
    }


    @Click
    void dataEntrada() {

        setEditTextDatePicker(dataEntrada);
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

    public void setEditTextDatePicker(final MaterialEditText text) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSelectedListener(new DatePickerFragment.OnDateSelectedListener() {
            @Override
            public void onDateSelected(DatePicker view, String formattedDate) {
                text.setText(formattedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "date picker");
    }


    public void salvarMorador() {

        morador = new Morador();
        morador.setNome(edtMorador.getText().toString());
        morador.setDataEntrada(dataEntrada.getText().toString());
        morador.setEmail(edtEmail.getText().toString());
        morador.setTelefone(edtTelefone.getText().toString());
        morador.setWhats(edtWhats.getText().toString());
        morador.setDataSaida(dataSaida.getText().toString());
        Quarto id =  new Select().from(Quarto.class).where("numero = ?", quarto.getNumero()).executeSingle();
        morador.setQuartoId(id.getId());
        if (moradorExiste(morador.getTelefone())) {
            Toast.makeText(this, "Morador ja cadastrado em outro quarto.", Toast.LENGTH_SHORT).show();
        } else {
            try {
                morador.save();
            } catch (Exception e) {
                Toast.makeText(this, "NAO FOI POSSIVEL SALVAR MORADOR" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void salvarQuarto() {

        quarto = new Quarto();
        quarto.setNome(edtIdentificacaoQuarto.getText().toString());
        quarto.setPreco(100.00);
        quarto.setStatus(0);
         quarto.setQuantidadeCamas(1);
         quarto.setNumero(10);
        quarto.setDescricao("quarto grande.");
        if (quartoJaExiste(quarto.getNumero())){
            Toast.makeText(this, "O quarto " + quarto.getNome() + " já foi cadastrado." , Toast.LENGTH_SHORT).show();
        }else
        {
            quarto.save();
            Toast.makeText(this, "Quarto Salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }

        if (!vago){
            salvarMorador();
        }


    }

    public void test() {
        edtMorador.setText("João da Silva");
        edtEmail.setText("wislen.souza@aedu.com");
        edtTelefone.setText("988252650");
        edtWhats.setText("988252650");
        edtPreco.setText("500,00");
    }

    public boolean moradorExiste(String whats) {

        if (whats.isEmpty()){
            return false;
        }
        try {
            return new Select().from(Morador.class).where("whats = ?", whats).exists();
        } catch (Exception e) {
            Toast.makeText(this, "NAO FOI POSSIVEL VERIFICAR SE O MORADOR DO EMAIL" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean quartoJaExiste(Integer numero){
        if (numero == null){
            return false;
        }
        try {
            return new Select().from(Quarto.class).where("nome = ?", numero).exists();
        } catch (Exception e) {
            Toast.makeText(this, "NAO FOI POSSIVEL VERIFICAR QUARTO POR NOME" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
           /* if (!moradorExiste(morador.getWhats() )  && !vago){
                salvarQuarto();
            }else{
                Toast.makeText(this, "Morador ja cadastrado em outro quarto.", Toast.LENGTH_SHORT).show();
            }*/
            MainActivity_.intent(this).start();
           // MainActivity_.intent(this).start();
            return true;
        }


        return super.onOptionsItemSelected(item);
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
}
