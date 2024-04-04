package com.example.ava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ava2.model.Funcionario;
import com.example.ava2.persistence.FuncionarioDAO;
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

        Funcionario func = new Funcionario();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func.setNome(nome_input.getText().toString().trim());
                func.setEmail(email_input.getText().toString().trim());
                func.setIdade(Integer.valueOf(idade_input.getText().toString().trim()));
                func.setCargo(cargo_input.getText().toString().trim());
                func.setSalario(Double.valueOf(salario_input.getText().toString().trim()));

                FuncionarioDAO dao = new FuncionarioDAO(AddFuncionario.this);

                dao.addFuncionario(func);

            }
        });

    }
}