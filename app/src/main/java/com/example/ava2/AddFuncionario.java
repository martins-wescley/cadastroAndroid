package com.example.ava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ava2.persistence.MyDatabaseHelper;

public class AddFuncionario extends AppCompatActivity {

    EditText nome_input, email_input, idade_input, cargo_input, salario_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funcionario);

        nome_input = findViewById(R.id.nome_input);
        email_input = findViewById(R.id.email_input);
        idade_input = findViewById(R.id.idade_input);
        cargo_input = findViewById(R.id.cargo_input);
        salario_input = findViewById(R.id.salario_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddFuncionario.this);
                myDatabaseHelper.addFuncionario(nome_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        Integer.valueOf(idade_input.getText().toString().trim()),
                        cargo_input.getText().toString().trim(),
                        Double.valueOf(salario_input.getText().toString().trim()));
            }
        });

    }
}