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

public class ChamadoActivity extends AppCompatActivity {

    private Button formularioBtn;
    private Spinner Emissor;
    private Spinner Receptor;
    private TextInputEditText formularioAssunto;
    private TextInputEditText formularioDescricao;
    private RadioButton radioBotao;
    private RadioGroup radioGrupo;
    private String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        Button btn = findViewById(R.id.formulario_btn);
        Emissor = findViewById(R.id.spinner_emissor);
        Receptor = findViewById(R.id.spinner_receptor);
        formularioAssunto = findViewById(R.id.formulario_assunto);
        formularioDescricao = findViewById(R.id.formulario_descricao);
        radioGrupo = findViewById(R.id.radioGroup);
        int radioid = radioGrupo.getCheckedRadioButtonId();

        radioBotao = findViewById(radioid);


        String[] usuarios = {"Allison", "Eclesio", "Fernando"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, usuarios);

        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Emissor.setAdapter(spinnerArrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void checkButton(View v){
        int radioid = radioGrupo.getCheckedRadioButtonId();

        radioBotao = findViewById(radioid);

    }
}
