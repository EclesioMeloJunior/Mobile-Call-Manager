package com.example.controledechamadosapp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDAO extends SQLiteOpenHelper {
    public UsuarioDAO(Context context) {
        super(context, "Controle de chamadas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usuario (id integer primary key," +
                "nome text not null," +
                "email text," +
                "endereco ," +
                "telefone text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
