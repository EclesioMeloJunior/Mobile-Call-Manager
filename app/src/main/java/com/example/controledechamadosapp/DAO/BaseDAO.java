package com.example.controledechamadosapp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDAO extends SQLiteOpenHelper {

    public BaseDAO(Context context) {
        super(context, "ControleChamadasTeste", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table usuarios (" +
            "id integer primary key," +
            "nome text not null," +
            "email text," +
            "telefone text)";

        db.execSQL(sql);

        String sqlChamado = "create table chamados (" +
            "id_chamado integer primary key," +
            "assunto text not null," +
            "descricao text,"+
            "idUserRemetente integer," +
            "idUserDestinatario integer," +
            "dataCriacao text," +
            "status integer," +
            "FOREIGN KEY (idUserRemetente) REFERENCES usuarios(id)," +
            "FOREIGN KEY (idUserDestinatario) REFERENCES usuarios(id))";

        db.execSQL(sqlChamado);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS chamados");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
