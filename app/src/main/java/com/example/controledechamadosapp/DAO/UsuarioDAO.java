package com.example.controledechamadosapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controledechamadosapp.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends SQLiteOpenHelper {
    public UsuarioDAO(Context context) {
        super(context, "Controle de chamadas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usuarios (id integer primary key," +
                "nome text not null," +
                "email text," +
                "telefone text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", usuario.getNome());
        dados.put("email", usuario.getEmail());
        dados.put("telefone", usuario.getTelefone());
        db.insert("usuarios", null, dados);
    }

    public void deletar(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(usuario.getId())};
        db.delete("usuarios","id = ?", parametros);
    }

    public void alterar(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", usuario.getNome());
        dados.put("email", usuario.getEmail());
        dados.put("telefone", usuario.getTelefone());

        String[] parametros = {String.valueOf(usuario.getId())};
        db.update("usuarios", dados, "id = ?", parametros);
    }

    public List<Usuario> listarUsuarios()
    {
        String sql = "Select * from usuarios;";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Usuario> usuarios = new ArrayList<>();
        while (c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(c.getString(c.getColumnIndex("email")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            usuario.setId(c.getInt(c.getColumnIndex("id")));

            usuarios.add(usuario);
        }

        return usuarios;
    }

}
