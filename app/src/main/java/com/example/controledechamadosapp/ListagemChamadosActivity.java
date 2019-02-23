package com.example.controledechamadosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.controledechamadosapp.Helpers.ChamadosAdapterPersonalizado;
import com.example.controledechamadosapp.Model.Chamado;

import java.util.ArrayList;
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
                Intent listUsuarios = new Intent(this, FormularioUsuarioActivity.class);
                startActivity(listUsuarios);
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_chamados);

        List<Chamado> chamados = new ArrayList<>();

        chamados.add(new Chamado("Problema na Impressora", "Houve algum tipo de probelma com o drive da impressora, por favor vir na minha sala urgentemente"));
        chamados.add(new Chamado("Problema na Impressora", "Houve algum tipo de probelma com o drive da impressora, por favor vir na minha sala urgentemente"));
        chamados.add(new Chamado("Computador com Tela Azul", "Houve algum tipo de probelma com o drive da impressora, por favor vir na minha sala urgentemente"));
        chamados.add(new Chamado("Máquina sem Acesso à internete", "Houve algum tipo de probelma com o drive da impressora, por favor vir na minha sala urgentemente"));
        chamados.add(new Chamado("Erro #404 na busca", "Houve algum tipo de probelma com o drive da impressora, por favor vir na minha sala urgentemente"));

        this.lvChamados = (ListView) findViewById(R.id.lvChamados);

        ChamadosAdapterPersonalizado adapterChamados = new ChamadosAdapterPersonalizado(chamados, this);
        this.lvChamados.setAdapter(adapterChamados);
    }
}
