package com.example.deyvi.gerenciamentoderepublica.firebase;

import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;
import com.google.firebase.database.DatabaseReference;

public class FireBaseManager {

    private static FireBaseManager INSTANCE;


    public static synchronized FireBaseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FireBaseManager();
        }

        return INSTANCE;
    }

    public void saveLocatario(Locador locador,String id){
     DatabaseReference reference = ConfigFireBase.getFireBase();
     reference.child("LOCADOR").child(id).setValue(locador);
    }




}
