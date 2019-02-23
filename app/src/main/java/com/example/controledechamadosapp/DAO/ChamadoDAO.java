package com.example.controledechamadosapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controledechamadosapp.Model.Chamado;

import java.text.SimpleDateFormat;

public class ChamadoDAO extends SQLiteOpenHelper {
    public ChamadoDAO(Context context) {
        super(context, "Controle de chamadas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table chamados (id integer primary key," +
                "assunto text not null," +
                "descricao text)";
                //"dataCriacao date ," +
                //"idEmissor integer ," +
                //"idReceptor integer," +
                //"status integer," +
                //"FOREIGN KEY (idEmissor) REFERENCES usuarios(id)," +
                //"FOREIGN KEY (idReceptor) REFERENCES usuarios(id)
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Chamado chamado){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(chamado.getData_criacao());

        dados.put("assunto", chamado.getAssunto());
        dados.put("descricao", chamado.getDescricao());
      //  dados.put("dataCriacao", date);
       // dados.put("idEmissor", chamado.getUsuarioLancamento().getId());
       // dados.put("idReceptor", chamado.getUsuarioDestino().getId());
        //dados.put("status", chamado.getStatus().ordinal());
        //db.insert("chamados", null, dados);
    }


}
