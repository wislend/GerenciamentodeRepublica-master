package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.fragments.CadastroImovelFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_imovel)
public class CadastroImovelActivity extends BaseActivity {


    @ViewById
    Toolbar toolbar;


    @AfterViews
    void initCadastroImovelFrag() {

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_content_clear);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fg1, CadastroImovelFragment_.builder().build());
        ft.commit();

    }

    //Class de comunicao entre o activiyt e fragmento com event bus.
    public class DataCommunication {
        DataCommunication() {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastrar_quarto_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salvar) {
            EventBus.getDefault().post(new DataCommunication());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Descartar seu cadastro?")
                .setMessage("As informações preenchidas até agora serão perdidas.")
                .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CadastroImovelActivity.super.onBackPressed();
                    }
                }).setNegativeButton("Cancelar", null)
                .show();
    }
}