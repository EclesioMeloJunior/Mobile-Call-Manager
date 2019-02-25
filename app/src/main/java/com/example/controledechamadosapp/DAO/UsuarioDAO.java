package com.example.controledechamadosapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends SQLiteOpenHelper {
    public UsuarioDAO(Context context) {
        super(context, "ControleChamadas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usuarios (id integer primary key," +
                "nome text not null," +
                "email text," +
                "telefone text)";

        db.execSQL(sql);

        String sqlChamado = "create table chamados (id integer primary key," +
                "assunto text not null," +
                "descricao text,"+
        //"dataCriacao date ," +
                "idUserRemetente integer ," +
                "idUserDestinatario integer," +
        //"status integer," +
                "FOREIGN KEY (idUserRemetente) REFERENCES usuarios(id)," +
                "FOREIGN KEY (idUserDestinatario) REFERENCES usuarios(id))";
        db.execSQL(sqlChamado);
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

    public List<Usuario> popularSpinner(){
        String sql = "Select id, nome from usuarios;";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Usuario> usuarios = new ArrayList<>();
        while (c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(c.getInt(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));

            usuarios.add(usuario);
        }
        return usuarios;
    }

    public void inserirChamado(Chamado chamado){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String date = sdf.format(chamado.getData_criacao());

        dados.put("assunto", chamado.getAssunto());
        dados.put("descricao", chamado.getDescricao());
        //  dados.put("dataCriacao", date);
        dados.put("idUserRemetente", chamado.getUsuarioLancamento().getId());
        dados.put("idUserDestinatario", chamado.getUsuarioDestino().getId());
        //dados.put("status", chamado.getStatus().ordinal());
        db.insert("chamados", null, dados);
    }


    public List<Chamado> listarChamado()
    {
        String sql = "select * from chamados inner join usuarios on chamados.idUserDestinatario = usuarios.id;";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Chamado> chamados = new ArrayList<>();
        while (c.moveToNext()) {
            Chamado chamado = new Chamado();
            chamado.setAssunto(c.getString(c.getColumnIndex("assunto")));
            chamado.setDescricao(c.getString(c.getColumnIndex("descricao")));
            chamados.add(chamado);
        }

        return chamados;
    }


}
