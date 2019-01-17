package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.QuartoAdapter;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.bll.Quartos;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardQuartosCadastradosRowView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_quartos)
public class QuartosActivity extends BaseActivity implements CardQuartosCadastradosRowView.OnClickManipulacaoQuartos {


    @Extra
    Long imovelId;

    @Extra
    Imovel imovel;

    @ViewById
    ListView listView;

    private QuartoAdapter quartoAdapter;

    private List<Quarto> listQuartos= new ArrayList<>();

    private Quartos quartos;


    @AfterViews
    void afterView() {
        this.setTitle(imovel.getNome());
        carregarQuartoDoImovel(imovelId);


    }

    List<Quarto> test() {

        List<Quarto> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Quarto quarto = new Quarto();
            quarto.setNome("Quarto" + i);
            list.add(quarto);
        }

        return list;
    }


    @Background
    void carregarQuartoDoImovel(Long id) {
        quartos = new Quartos();

        try {
            respostaCarregarQuartoDoImovel(quartos.listQuartosForId(id), null);

        } catch (Exception e) {
            respostaCarregarQuartoDoImovel(null, e);
        }

    }

    @UiThread
    void respostaCarregarQuartoDoImovel(List<Quarto> quartos, Exception e) {
        if (e != null) {
            DbLogs.Log("Não carregou a lista dos quartos", e, "");
        }
        listQuartos = quartos;

        if (listQuartos == null || listQuartos.size() == 0) {
            quartoAdapter = new QuartoAdapter(this, test());
            listView.setAdapter(quartoAdapter);
        } else {
            quartoAdapter = new QuartoAdapter(this, listQuartos);
            listView.setAdapter(quartoAdapter);
        }

        quartoAdapter.setOnClickManipulacaoQuartos(this);
    }

    @Background
    void excluirQuarto(Quarto quarto) {
        Quartos quartos = new Quartos();
        try {
           quartos.deletarQuarto(quarto);
        } catch (Exception e) {
            excluirImovelResposta(e);
        }
    }

    @UiThread
    void excluirImovelResposta(Exception e) {
        if (e != null) {
            DbLogs.Log("Erro ao tentar deletar imovel", e, "");
        }
    }


    @Override
    public void onClickDelete(final Quarto quarto) {
        new AlertDialog.Builder(this)
                .setTitle("Deletar Quarto?")
                .setMessage("Você tem ceteza que deseja deletar " + quarto.getNome())
                .setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        quartoAdapter.remove(quarto);
                        excluirQuarto(quarto);
                        Toast.makeText(getApplication(), "Deletado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public void onClickEdite(CardQuartosCadastradosRowView cadastradosRowView, int position, Quarto quarto) {
            DetalhesQuartoActivity_.intent(this).quarto(quarto).start();
    }

    @Override
    public void onClickDetailsQuarto(Quarto quarto) {

    }

    @Override
    public void onClickFavoritar(Quarto quarto) {

    }
}
