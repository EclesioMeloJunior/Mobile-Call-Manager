package com.example.controledechamadosapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controledechamadosapp.DAO.ChamadoDAO;
import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.Usuario;

import java.util.List;

public class ChamadoActivity extends AppCompatActivity {

    private Button formularioBtn;
    private Spinner Emissor;
    private Spinner Receptor;
    private TextInputEditText formularioAssunto;
    private TextInputEditText formularioDescricao;
    Chamado chamado = new Chamado();
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        formularioBtn = findViewById(R.id.formulario_btn);
        Emissor = findViewById(R.id.spinner_emissor);
        Receptor = findViewById(R.id.spinner_receptor);
        formularioAssunto = findViewById(R.id.formulario_assunto);
        formularioDescricao = findViewById(R.id.formulario_descricao);

        popularEmissor();
        popularReceptor();

        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamado.setAssunto(formularioAssunto.getText().toString());
                chamado.setDescricao(formularioDescricao.getText().toString());

               // Usuario usuarioEmissor = (Usuario) Emissor.getSelectedItem();
                //int idEmissor = usuarioEmissor.getId();
                //usuarioEmissor.setId(idEmissor);
                //chamado.setUsuarioDestino(usuarioEmissor);

              //  Usuario usuarioReceptor = (Usuario) Emissor.getSelectedItem();
                //int idReceptor = usuarioReceptor.getId();
              //  usuarioReceptor.setId(idReceptor);
               // chamado.setUsuarioDestino(usuarioReceptor);


                ChamadoDAO chamadoDAO = new ChamadoDAO(ChamadoActivity.this);

                chamadoDAO.inserir(chamado);

                chamadoDAO.close();

                Toast.makeText(ChamadoActivity.this, "Chamado registrado com sucesso!", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

    public void popularEmissor(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.popularSpinner();
        ArrayAdapter<Usuario> arrayAdapterEmissor = new ArrayAdapter<Usuario>(this,
                android.R.layout.simple_spinner_item, usuarios);
        Emissor.setAdapter(arrayAdapterEmissor);
    }

    public void popularReceptor(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List<Usuario> usuarios = usuarioDAO.popularSpinner();
        ArrayAdapter<Usuario> arrayAdapterReceptor = new ArrayAdapter<Usuario>(this,
                android.R.layout.simple_spinner_item, usuarios);
        Receptor.setAdapter(arrayAdapterReceptor);
    }



}



