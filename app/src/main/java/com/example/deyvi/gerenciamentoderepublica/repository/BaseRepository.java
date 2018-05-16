package com.example.deyvi.gerenciamentoderepublica.repository;

import android.content.Context;

public class BaseRepository {

    public static BaseRepository INSTANCE;
    private BaseDbHelper mBaseDbHelper;

    private BaseRepository(Context context) {
        mBaseDbHelper = new BaseDbHelper(context);
    }

    public static synchronized BaseRepository getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = new BaseRepository(context);
        }
        return INSTANCE;
    }
/*
    public void insert(Convidado convidado) {
        try{
            SQLiteDatabase sqLiteDatabase = mBaseDbHelper.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            contentValues.put(DbConst.NAME_CONVIDADE,convidado.getNome());
            contentValues.put(DbConst.PRESENCE,convidado.getStatus());
            sqLiteDatabase.insert(DbConst.DATA_BASE_NAME,null,contentValues);
            Log.e("BANCO DE DADOS","DADO INSERIDO COM SUCESSO");

        }catch (Exception e){
            Log.e("BANCO DE DADOS",e.getMessage());
        }
    }*/
}
