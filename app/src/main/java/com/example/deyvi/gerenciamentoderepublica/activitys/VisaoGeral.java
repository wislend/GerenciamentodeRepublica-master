package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseDrawer;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.LoadersAdapter;
import com.example.deyvi.gerenciamentoderepublica.adapters.ImoveisAdapter;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.views.row.CardImoveisCadastradosRowView;
import com.pixplicity.easyprefs.library.Prefs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
@EActivity(R.layout.activity_visao_geral)
public class VisaoGeral extends BaseDrawer implements
        LoaderManager.LoaderCallbacks<List<Imovel>>,
        CardImoveisCadastradosRowView.OnClickManipulacaoImoveis {


    @ViewById(R.id.listView)
    ListView listView;
    ImoveisAdapter mImoveisAdapter;


    @ViewById
    Toolbar toolbar;

    @AfterViews
    void init() {

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        showProgressDialog();
        listarImoveis();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Background
    void listarImoveis() {
        Imoveis imoveis = new Imoveis();
        try {
            listarImoveisResposta(imoveis.listImoveis(), null);
        } catch (Exception e) {
            listarImoveisResposta(null, e);
        }

    }


    @UiThread
    void listarImoveisResposta(List<Imovel> list, Exception e) {
        if (e != null) {
            DbLogs.Log("Não foi possível trazer lista de imoveis", e, "");
            return;
        }
        mImoveisAdapter = new ImoveisAdapter(this, list);
        listView.setAdapter(mImoveisAdapter);
        mImoveisAdapter.setOnClickManipulacaoImoveis(this);
        getSupportLoaderManager().initLoader(1, null, this);
        dismissProgressDialog();
    }


    List<Imovel> test() {

        List<Imovel> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Imovel imovel = new Imovel();
            imovel.setNome("imovel" + i);
            imovel.setJurosDia(100);
            imovel.setJurosMes(120);
            imovel.setAlugado(true);
            imovel.setQuantQuartos(i);
            imovel.setValor(155500);
            list.add(imovel);
        }

        return list;
    }


    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Imovel>> onCreateLoader(int id, @Nullable Bundle args) {
        return new LoadersAdapter(VisaoGeral.this);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Imovel>> loader, List<Imovel> data) {
        mImoveisAdapter.setImoveis(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<List<Imovel>> loader) {
        mImoveisAdapter.setImoveis(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSair:
                Prefs.putBoolean("LOGADO", false);
                finish();
                return true;
            case R.id.actionAddImovel:
                CadastroImovelActivity_.intent(this).start();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickDelete(final Imovel imovel) {
        new AlertDialog.Builder(this)
                .setTitle("Deletar Imovél?")
                .setMessage("Você tem ceteza que deseja deletar " + imovel.getNome())
                .setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mImoveisAdapter.remove(imovel);
                        excluirImovel(imovel);
                        Toast.makeText(getApplication(), "Deletado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", null)
                .show();

    }

    @Background
    void excluirImovel(Imovel imovel) {
        Imoveis imoveis = new Imoveis();
        try {
            imoveis.deleteImovel(imovel);
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
    public void onClickEdite(CardImoveisCadastradosRowView cadastradosRowView, int position, Imovel imovel) {
        DetalhesQuartoActivity_.intent(this).start();
        Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClickAddQuarto(final Imovel imovel) {
        CadastroQuartoActivity_.intent(this).imovelId(imovel.getId()).start();
    }

    @Override
    public void onClickDetailsQuarto(Imovel imovel) {
        Toast.makeText(this, "Detalhes", Toast.LENGTH_SHORT).show();
        QuartosActivity_.intent(this).imovel(imovel).start();
    }
}
