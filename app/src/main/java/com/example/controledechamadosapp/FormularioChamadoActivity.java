package com.example.controledechamadosapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controledechamadosapp.DAO.ChamadoDAO;
import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.ChamadoStatus;
import com.example.controledechamadosapp.Model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FormularioChamadoActivity extends AppCompatActivity {

    private Button formularioBtn;
    private Spinner spinerRemetente;
    private Spinner spinnerDestinatario;
    private TextInputEditText formularioAssunto;
    private TextInputEditText formularioDescricao;
    private Spinner chamadoStatusSpinner;

    Chamado chamado = new Chamado();
    Usuario usuario = new Usuario();

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_chamado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_deletar_chamado:
                if(chamado.getId() != 0)
                {
                    new ChamadoDAO(this).deletarChamado(chamado.getId());
                    finish();
                }
                else
                {
                    Toast.makeText(FormularioChamadoActivity.this, "Não é possível deletar!", Toast.LENGTH_LONG).show();
                }
                break;

        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.activity_chamado);

        formularioBtn = findViewById(R.id.formulario_btn);
        spinerRemetente = findViewById(R.id.spinner_emissor);
        spinnerDestinatario = findViewById(R.id.spinner_receptor);
        formularioAssunto = findViewById(R.id.formulario_assunto);
        formularioDescricao = findViewById(R.id.formulario_descricao);
        chamadoStatusSpinner = findViewById(R.id.sp_chamado_status);

        String[] possiveisStatus = new String[] { "Aberto", "Em Andamento", "Fechado" };

        ArrayAdapter<String> arrayAdapterEmissor = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, possiveisStatus);

        chamadoStatusSpinner.setAdapter(arrayAdapterEmissor);

        if(intent.hasExtra("chamado")){
            chamado = (Chamado) intent.getSerializableExtra("chamado");

            int positionEmissor = popularEmissor(chamado.getUsuarioLancamento());
            int positionReceptor = popularReceptor(chamado.getUsuarioDestino());

            spinerRemetente.setSelection(positionEmissor);
            spinnerDestinatario.setSelection(positionReceptor);

            chamadoStatusSpinner.setSelection(chamado.getStatus().ordinal());

            formularioAssunto.setText(chamado.getAssunto());
            formularioDescricao.setText(chamado.getDescricao());

        }else{
            chamado = new Chamado();
            popularEmissor(null);
            popularReceptor(null);
        }

        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chamado.setAssunto(formularioAssunto.getText().toString());
                chamado.setDescricao(formularioDescricao.getText().toString());

                Usuario usuarioEmissor = new Usuario();
                int idEmissor = ((Usuario) spinerRemetente.getSelectedItem()).getId();
                usuarioEmissor.setId(idEmissor);
                chamado.setUsuarioLancamento(usuarioEmissor);


                int idReceptor = ((Usuario) spinnerDestinatario.getSelectedItem()).getId();
                Usuario usuarioReceptor = new Usuario();
                usuarioReceptor.setId(idReceptor);
                chamado.setUsuarioDestino(usuarioReceptor);

                switch (chamadoStatusSpinner.getSelectedItem().toString())
                {
                    case "Aberto":
                    {
                        chamado.setStatus(ChamadoStatus.ABERTO);
                        break;
                    }

                    case "Em Andamento":
                    {
                        chamado.setStatus(ChamadoStatus.EM_ANDAMENTO);
                        break;
                    }

                    case "Fechado":
                    {
                        chamado.setStatus(ChamadoStatus.FECHADO);
                        break;
                    }
                }

                chamado.setData_criacao(new Date());

                ChamadoDAO chamadoDAO = new ChamadoDAO(FormularioChamadoActivity.this);
                chamadoDAO.inserirChamado(chamado);
                chamadoDAO.close();

                Toast.makeText(FormularioChamadoActivity.this, "Chamado registrado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public int popularEmissor(Usuario emissor){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.popularSpinner();

        ArrayAdapter<Usuario> arrayAdapterEmissor = new ArrayAdapter<Usuario>(this,
                android.R.layout.simple_spinner_item, usuarios);

        spinerRemetente.setAdapter(arrayAdapterEmissor);

        if(emissor != null)
        {
            Usuario posicoa = null;

            for (Usuario usuario : usuarios)
            {
                if(usuario.getId() == emissor.getId())
                {
                    posicoa = usuario;
                }
            }
            if(usuario != null) return arrayAdapterEmissor.getPosition(posicoa);
        }

        return -1;
    }

    public int popularReceptor(Usuario receptor){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.popularSpinner();

        ArrayAdapter<Usuario> arrayAdapterReceptor = new ArrayAdapter<Usuario>(this,
                android.R.layout.simple_spinner_item, usuarios);

        spinnerDestinatario.setAdapter(arrayAdapterReceptor);

        if(receptor != null)
        {
            Usuario posicoa = null;

            for (Usuario usuario : usuarios)
            {
                if(usuario.getId() == receptor.getId())
                {
                    posicoa = usuario;
                    break;
                }
            }

            if(usuario != null) return arrayAdapterReceptor.getPosition(posicoa);
        }
        return -1;
    }

}



