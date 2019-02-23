package com.example.controledechamadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();
    private EditText nomeUsuario;
    private EditText emailUsuario;
    private EditText telefoneUsuario;
    private Button formUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        nomeUsuario = findViewById(R.id.nomeUsuario);
        emailUsuario = findViewById(R.id.email_usuario);
        telefoneUsuario = findViewById(R.id.telefone_usuario);

        formUsuario = findViewById(R.id.formUsuario);

        formUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario.setNome(nomeUsuario.getText().toString());
                usuario.setEmail(emailUsuario.getText().toString());
                usuario.setTelefone(telefoneUsuario.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(FormularioUsuarioActivity.this);

                usuarioDAO.inserir(usuario);

                if(usuario.getId() == 0){
                    usuarioDAO.inserir(usuario);
                }
                else{
                    formUsuario.setText("Alterar");
                    usuarioDAO.alterar(usuario);
                }

                usuarioDAO.close();
                //MOSTRAR MENSAGEM
                Toast.makeText(FormularioUsuarioActivity.this, "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();

                //Destruir a Activity
                finish();
            }
        });




    }
}
