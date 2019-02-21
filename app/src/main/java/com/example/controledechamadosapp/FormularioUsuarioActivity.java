package com.example.controledechamadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.controledechamadosapp.Model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();
    private EditText nomeUsuario;
    private EditText emailUsuario;
    private EditText telefoneUsuario;
    private RadioButton cargoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        nomeUsuario = findViewById(R.id.nome_usuario);
        emailUsuario = findViewById(R.id.email_usuario);
        telefoneUsuario = findViewById(R.id.telefone_usuario);
        cargoUsuario = findViewById(R.id.opcao1_cargo);
        cargoUsuario = findViewById(R.id.opcao2_cargo);



    }
}
