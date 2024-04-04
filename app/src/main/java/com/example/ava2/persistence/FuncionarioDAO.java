package com.example.ava2.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.ava2.model.Funcionario;

public class FuncionarioDAO {

    Context context;
    private MyDatabaseHelper myDatabaseHelper;

    public FuncionarioDAO(Context context) {
        this.context = context;
    }

    public void addFuncionario(Funcionario funcionario) {

        myDatabaseHelper = new MyDatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MyDatabaseHelper.COLUMN_NOME, funcionario.getNome());
        cv.put(MyDatabaseHelper.COLUMN_EMAIL, funcionario.getEmail());
        cv.put(MyDatabaseHelper.COLUMN_IDADE, funcionario.getIdade());
        cv.put(MyDatabaseHelper.COLUMN_CARGO, funcionario.getCargo());
        cv.put(MyDatabaseHelper.COLUMN_SALARIO, funcionario.getSalario());

        long result = sqLiteDatabase.insert(MyDatabaseHelper.TABLE_NAME, null, cv);

        if(result == -1) {

            Toast.makeText(context, "Erro ao Adicionar Funcionário", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Funcionário Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        myDatabaseHelper = new MyDatabaseHelper(context);
        String query = "SELECT _id, nome, cargo, idade FROM " + MyDatabaseHelper.TABLE_NAME;
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readUpdateData(int id) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        String query = "SELECT nome, email, idade, cargo, salario FROM " + MyDatabaseHelper.TABLE_NAME + " WHERE _id = " + id;
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateFuncionario(Funcionario funcionario, String row_id) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MyDatabaseHelper.COLUMN_NOME, funcionario.getNome());
        cv.put(MyDatabaseHelper.COLUMN_EMAIL, funcionario.getEmail());
        cv.put(MyDatabaseHelper.COLUMN_IDADE, funcionario.getIdade());
        cv.put(MyDatabaseHelper.COLUMN_CARGO, funcionario.getCargo());
        cv.put(MyDatabaseHelper.COLUMN_SALARIO, funcionario.getSalario());

        long result = db.update(MyDatabaseHelper.TABLE_NAME, cv, " _id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Erro ao atualizar", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Funcionário Atualizado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
        long result = db.delete(MyDatabaseHelper.TABLE_NAME, " _id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Erro ao deletar", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Funcionário deletado.", Toast.LENGTH_SHORT).show();
        }
    }
}
