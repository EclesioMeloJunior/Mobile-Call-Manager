package com.example.controledechamadosapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.controledechamadosapp.DAO.ChamadoDAO;
import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Helpers.ChamadosAdapterPersonalizado;
import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.Usuario;

import java.util.List;

public class ListagemChamadosActivity extends AppCompatActivity {

    private ListView lvChamados;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_criar_usuario:
                Intent criarUsuarios = new Intent(this, FormularioUsuarioActivity.class);
                startActivity(criarUsuarios);
                break;

            case R.id.listar_usuarios:
                Intent listUsuarios = new Intent(this, ListarUsuariosActivity.class);
                startActivity(listUsuarios);
                break;

            case R.id.criar_chamado:
                Intent criarChamado = new Intent(this, FormularioChamadoActivity.class);
                startActivity(criarChamado);
                break;

        }

        return true;
    }

    public void GetAllChamados()
    {
        this.lvChamados = findViewById(R.id.lvChamados);

        ChamadoDAO chamadoDAO = new ChamadoDAO(this);

        List<Chamado> chamados = chamadoDAO.listarChamados();

        ChamadosAdapterPersonalizado adapter = new ChamadosAdapterPersonalizado(chamados, this);

        lvChamados.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        setContentView(R.layout.activity_listagem_chamados);

        this.GetAllChamados();

        lvChamados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Chamado chamado = (Chamado) lista.getItemAtPosition(posicao);

                chamado = new ChamadoDAO(ListagemChamadosActivity.this).getChamadoById(chamado.id);

                Intent intent = new Intent(ListagemChamadosActivity.this, FormularioChamadoActivity.class);
                intent.putExtra("chamado", chamado);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.GetAllChamados();
    }
}
