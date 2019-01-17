package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.devspark.robototextview.widget.RobotoTextView;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseImovelDetalhesPageFragment;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Enderecos;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.cadastro_imovel_fragment)
public class DetalhesImovelFragment extends BaseImovelDetalhesPageFragment {

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
    private Uri fileUri;

    //endregion
    private Moveis moveis;
    private Movel movel;


    private Quarto quarto;


    public DetalhesImovelFragment() {
    }

    @AfterViews
    void afterViews() {
        moveis = new Moveis();
        quarto = new Quarto();
        txtTitulo.setVisibility(View.GONE);
        if (getImovel() != null) {
            edtNomeImovel.setText(getImovel().getNome());
            edtNumeroDeQuartos.setText(String.valueOf(getImovel().getQuantQuartos()));
            Picasso.get().load("file:///" + getImovel().getCaminhoImagem()).into(imgPhoto);
            if (getImovel().isAlugado()) {
                rdAlugado.setChecked(true);
                edtValorAluguel.setText(String.valueOf(getImovel().getValor()));
            } else {
                rdPropio.setChecked(true);
                edtValorAluguel.setVisibility(View.GONE);
            }

            carregarEndereco(1L);

        }

        naoEditar();


    }

    @Background
    void carregarEndereco(Long id) {
        Enderecos enderecos = new Enderecos();
        try {
            respostaCarregarEndereco(enderecos.selectEndereco(id), null);
        } catch (Exception e) {
            respostaCarregarEndereco(null, e);
        }

    }

    @UiThread
    void respostaCarregarEndereco(Endereco endereco, Exception e) {
        if (e != null) {
            DbLogs.Log("Não foi possivel carregar o endereco", e, "");
            return;
        }

        edtCep.setText(endereco.getCep());
        edtNumero.setText(endereco.getNumero());
        edtRua.setText(endereco.getRua());
        edtEstado.setText(endereco.getEstado());
        edtPontoReferencia.setText(endereco.getPontoReferencia());
        edtBairro.setText(endereco.getBairro());
        edtCidade.setText(endereco.getCidade());

    }


    void naoEditar() {
        edtCep.setEnabled(false);
        edtNumero.setEnabled(false);
        edtRua.setEnabled(false);
        edtEstado.setEnabled(false);
        edtPontoReferencia.setEnabled(false);
        edtBairro.setEnabled(false);
        edtCidade.setEnabled(false);
        edtNomeImovel.setEnabled(false);
        edtNumeroDeQuartos.setEnabled(false);
        edtValorAluguel.setEnabled(false);
    }


}
