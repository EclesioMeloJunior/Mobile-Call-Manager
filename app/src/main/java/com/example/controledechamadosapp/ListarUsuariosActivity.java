package com.example.controledechamadosapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Helpers.UsuariosAdpaterPersonalizado;
import com.example.controledechamadosapp.Model.Usuario;

import java.util.List;

public class ListarUsuariosActivity extends AppCompatActivity {

    private ListView listaUsuarios;
    Usuario usuario;
    UsuarioDAO usuarioDAO = new UsuarioDAO(ListarUsuariosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        listaUsuarios = findViewById(R.id.lv_listaUsuarios);

        ListarUsuarios();

        registerForContextMenu(listaUsuarios);

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                usuario = (Usuario) lista.getItemAtPosition(posicao);

                Intent intent = new Intent(ListarUsuariosActivity.this, FormularioUsuarioActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
    }

    private void ListarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        UsuariosAdpaterPersonalizado adapter = new UsuariosAdpaterPersonalizado(usuarios, this);

        listaUsuarios.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListarUsuarios();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Deletar");


        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo menu = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Usuario usuario = (Usuario) listaUsuarios.getItemAtPosition(menu.position);


                usuarioDAO.deletar(usuario);

                ListarUsuarios();

                return false;
            }
        });


    }
}
