package com.example.controledechamadosapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.ChamadoStatus;
import com.example.controledechamadosapp.Model.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChamadoDAO extends BaseDAO {
    private Context context;

    public ChamadoDAO(Context context) {
        super(context);
        this.context = context;
    }

    public void inserirChamado(Chamado chamado){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        if(chamado.getId() != 0)
        {
            dados.put("assunto", chamado.getAssunto());
            dados.put("descricao", chamado.getDescricao());
            dados.put("idUserRemetente", chamado.getUsuarioLancamento().getId());
            dados.put("idUserDestinatario", chamado.getUsuarioDestino().getId());
            dados.put("status", chamado.getStatus().ordinal());

            String[] parametros = {String.valueOf(chamado.getId())};
            db.update("chamados", dados, "id_chamado = ?", parametros);
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(chamado.getData_criacao());

            dados.put("assunto", chamado.getAssunto());
            dados.put("descricao", chamado.getDescricao());
            dados.put("dataCriacao", date);
            dados.put("idUserRemetente", chamado.getUsuarioLancamento().getId());
            dados.put("idUserDestinatario", chamado.getUsuarioDestino().getId());
            dados.put("status", chamado.getStatus().ordinal());

            db.insert("chamados", null, dados);
        }
    }

    public void deletarChamado(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        db.delete("chamados","id_chamado = ?", parametros);
    }

    public List<Chamado> listarChamados()
    {
        String sql = "select * from chamados inner join usuarios on chamados.idUserDestinatario = usuarios.id;";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Chamado> chamados = new ArrayList<>();

        while (c.moveToNext()) {
            ChamadoStatus status = ChamadoStatus.values()[c.getInt(c.getColumnIndex("status"))];

            Chamado chamado = new Chamado();

            chamado.setId(c.getInt(c.getColumnIndex("id_chamado")));
            chamado.setAssunto(c.getString(c.getColumnIndex("assunto")));
            chamado.setDescricao(c.getString(c.getColumnIndex("descricao")));
            chamado.setStatus(status);

            chamados.add(chamado);
        }

        return chamados;
    }

    public Chamado getChamadoById(int chamadoId) throws ParseException {
        String sql = "select * from chamados inner join usuarios on chamados.idUserDestinatario = usuarios.id where id_chamado = " + chamadoId + ";";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Chamado> chamados = new ArrayList<>();

        while (c.moveToNext()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(this.context);

            ChamadoStatus status = ChamadoStatus.values()[c.getInt(c.getColumnIndex("status"))];

            Chamado chamado = new Chamado();
            chamado.setId(c.getInt(c.getColumnIndex("id_chamado")));
            chamado.setAssunto(c.getString(c.getColumnIndex("assunto")));
            chamado.setDescricao(c.getString(c.getColumnIndex("descricao")));

            String string = c.getString(c.getColumnIndex("dataCriacao"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = format.parse(string);

            chamado.setData_criacao(date);

            Usuario usuarioDestinatario = usuarioDAO.getUsuarioById(c.getInt(c.getColumnIndex("idUserDestinatario")));
            Usuario usuairoRemetente = usuarioDAO.getUsuarioById(c.getInt(c.getColumnIndex("idUserRemetente")));

            chamado.setUsuarioDestino(usuarioDestinatario);
            chamado.setUsuarioLancamento(usuairoRemetente);

            chamado.setStatus(status);

            chamados.add(chamado);
        }

        return chamados.get(0);
    }
}
