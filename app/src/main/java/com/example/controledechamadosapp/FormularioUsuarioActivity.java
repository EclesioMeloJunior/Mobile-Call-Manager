package com.example.controledechamadosapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.controledechamadosapp.DAO.ChamadoDAO;
import com.example.controledechamadosapp.DAO.UsuarioDAO;
import com.example.controledechamadosapp.Model.Usuario;

public class FormularioUsuarioActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();
    private TextInputEditText nomeUsuario;
    private TextInputEditText emailUsuario;
    private TextInputEditText telefoneUsuario;
    private Button formUsuario;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_formulariousuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_deletar_usuario:
                if(usuario.getId() != 0)
                {
                    new UsuarioDAO(this).deletar(usuario);
                    finish();
                }
                else
                {
                    Toast.makeText(FormularioUsuarioActivity.this, "Não é possível deletar!", Toast.LENGTH_LONG).show();
                }
                break;

        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        formUsuario = findViewById(R.id.formUsuario);

        nomeUsuario = findViewById(R.id.nomeUsuario);
        emailUsuario = findViewById(R.id.email_usuario);
        telefoneUsuario = findViewById(R.id.telefone_usuario);

        Intent intent = getIntent();

        if(intent.hasExtra("usuario")){
            usuario = (Usuario) intent.getSerializableExtra("usuario");
        }else{
            usuario = new Usuario();
        }

        if (usuario != null){
            nomeUsuario.setText(usuario.getNome());
            emailUsuario.setText(usuario.getEmail());
            telefoneUsuario.setText(usuario.getTelefone());
        }

        formUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario.setNome(nomeUsuario.getText().toString());
                usuario.setEmail(emailUsuario.getText().toString());
                usuario.setTelefone(telefoneUsuario.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(FormularioUsuarioActivity.this);

                if(usuario.getId()!= 0){
                    usuarioDAO.alterar(usuario);
                    Toast.makeText(FormularioUsuarioActivity.this, "Alterado com sucesso",Toast.LENGTH_LONG).show();

                }else{
                    usuarioDAO.inserir(usuario);
                    Toast.makeText(FormularioUsuarioActivity.this, "Salvo com sucesso",Toast.LENGTH_LONG).show();
                }

                usuarioDAO.close();


                finish();


            }
        });
    }
}
