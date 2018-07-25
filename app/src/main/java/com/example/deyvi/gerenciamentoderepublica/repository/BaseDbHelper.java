package com.example.deyvi.gerenciamentoderepublica.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;


public class BaseDbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + SqliteConstantes.DATA_BASE_NAME + "(" +
                    SqliteConstantes.ID_CONVIDADO + " integer primary key, " +
                    SqliteConstantes.NAME_CONVIDADE + " text, "+
                    SqliteConstantes.PRESENCE + " integer)";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF NOT EXISTS " + SqliteConstantes.DATA_BASE_NAME;


    public BaseDbHelper(Context context) {
        super(context, SqliteConstantes.DATA_BASE_NAME, null, SqliteConstantes.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
