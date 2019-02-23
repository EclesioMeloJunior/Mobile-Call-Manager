package com.example.controledechamadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Helpers.UsuariosAdpaterPersonalizado;
import com.example.controledechamadosapp.Model.Usuario;

import java.util.List;

public class ListarUsuariosActivity extends AppCompatActivity {

    private ListView listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        listaUsuarios = findViewById(R.id.lv_listaUsuarios);

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        UsuariosAdpaterPersonalizado adapter = new UsuariosAdpaterPersonalizado(usuarios, this);

        listaUsuarios.setAdapter(adapter);
    }
}
