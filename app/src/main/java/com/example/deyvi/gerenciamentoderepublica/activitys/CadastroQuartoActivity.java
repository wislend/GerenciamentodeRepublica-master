package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar.DatePickerFragment;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.bll.Moradores;
import com.example.deyvi.gerenciamentoderepublica.bll.Quartos;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.google.android.flexbox.FlexboxLayout;
import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class CadastroQuartoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    int chId = 1000;

    String[] names = {"Cama", "Armário", "Rack", "Televisão", "Ar condicionado", "Vendilador"};
    CheckBox[] ch;


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
    android.support.v7.widget.Toolbar toolbar;

    @ViewById
    FlexboxLayout viewContainerCheckBox;

    private Quarto quarto;

    //bll 
    private Quartos quartos;

    private Long idQuarto;

    private Moradores moradores;

    private boolean vago = true;

    HashMap<CheckBox, Imovel> checkBoxMap = new HashMap<>();

    CheckBoxGroup<Imovel> checkBoxGroup;

    @AfterViews
    public void afterViews() {

        //MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE);
        // MaskEditUtil.insert(edtWhats, MaskEditUtil.MaskType.TELEFONE);
        setSupportActionBar(toolbar);
        test();
        radioOcupadoVago.setOnCheckedChangeListener(this);

        testCheck();


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
         moradores.salvarMorador(morador);
        }
    }

    public void salvarQuarto() {
        quartos = new Quartos();
        quarto = new Quarto();
        quarto.setNome(edtIdentificacaoQuarto.getText().toString());
        quarto.setPreco(100.00);
        quarto.setStatus(0);
        quarto.setQuantidadeCamas(1);
        quarto.setNumero(10);
        quarto.setDescricao("quarto grande.");

        if (quartos.quartoExiste(quarto.getNumero())){
            Toast.makeText(this, "O quarto " + quarto.getNome() + " já foi cadastrado." , Toast.LENGTH_SHORT).show();
        }else
        {
            idQuarto = quartos.salvarQuarto(quarto);
            Toast.makeText(this, SqliteConstantes.QUARTO_SALVO_SUCESSO, Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
           if (!moradores.moradorExiste(edtWhats.getText().toString())  && !vago){
                salvarQuarto();
            }else{
                Toast.makeText(this, "Moradores ja cadastrado em outro quarto.", Toast.LENGTH_SHORT).show();
                VisaoGeral_.intent(this).start();
            }

           // Apresentacao_Activity_.intent(this).start();
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



   void testCheck(){

       ch = new CheckBox[names.length];
       for(int i=0; i<names.length; i++) {
           ch[i] = new CheckBox(this);
           ch[i].setId(chId++);
           ch[i].setText(names[i]);
           viewContainerCheckBox.addView(ch[i]);
       }

       for (int i = 0; i < names.length; i++) {
           final int j = i;
           ch[j].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,
                                            boolean isChecked) {
                   String stado = isChecked ? "checado" : "não checado";
                   Toast.makeText(CadastroQuartoActivity.this, "chegck" + stado, Toast.LENGTH_SHORT).show();
               }
           });
       }
   }




}
