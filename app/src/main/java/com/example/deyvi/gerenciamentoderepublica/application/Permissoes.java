package com.example.deyvi.gerenciamentoderepublica.application;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissoes {

    public static boolean validaPermissoes(Integer requestCode, Activity activity,String[] permissoes){
        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissao = new ArrayList<>();
            for (String permisao : permissoes) {

                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity,permisao) == PackageManager.PERMISSION_GRANTED;
                if (!validaPermissao) {
                    listaPermissao.add(permisao);
                }
            }

            if (listaPermissao.size() ==  0){ return true;}
            String[] novasPermissoes = new String[listaPermissao.size()];
            listaPermissao.toArray(novasPermissoes);

            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }

        return true;
    }


}
