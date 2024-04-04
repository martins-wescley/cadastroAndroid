package com.example.ava2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ava2.model.Funcionario;
import com.example.ava2.persistence.FuncionarioDAO;
import com.example.ava2.persistence.MyDatabaseHelper;

public class UpdateFuncionario extends AppCompatActivity {

    TextView nome_update, email_update, idade_update, cargo_update, salario_update;
    Button update_button, delete_button;
    MyDatabaseHelper myDB;
    FuncionarioDAO dao;
    String novoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_funcionario);
        nome_update = findViewById(R.id.nome_update);
        email_update = findViewById(R.id.email_update);
        idade_update = findViewById(R.id.idade_update);
        cargo_update = findViewById(R.id.cargo_update);
        salario_update = findViewById(R.id.salario_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        myDB = new MyDatabaseHelper(UpdateFuncionario.this);
        Funcionario func = new Funcionario();
        dao = new FuncionarioDAO(UpdateFuncionario.this);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func.setNome(nome_update.getText().toString().trim());
                func.setEmail(email_update.getText().toString().trim());
                func.setIdade(Integer.valueOf(idade_update.getText().toString().trim()));
                func.setCargo(cargo_update.getText().toString().trim());
                func.setSalario(Double.valueOf(salario_update.getText().toString().trim()));

               dao.updateFuncionario(func, novoId);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")) {
            novoId = getIntent().getStringExtra("id");
            Cursor cursor = dao.readUpdateData(Integer.parseInt(novoId));

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Sem Registro na Base da Dados", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    nome_update.setText(cursor.getString(0));
                    email_update.setText(cursor.getString(1));
                    idade_update.setText(cursor.getString(2));
                    cargo_update.setText(cursor.getString(3));
                    salario_update.setText(cursor.getString(4));
                }
            }
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar Funcionário");
        builder.setMessage("Deseja deletar o funcionário " + nome_update.getText().toString().trim() + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteOneRow(novoId);
                Intent intent = new Intent(UpdateFuncionario.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

}