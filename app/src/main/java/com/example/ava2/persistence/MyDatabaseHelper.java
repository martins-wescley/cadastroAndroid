package com.example.ava2.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "EmpresaUva.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Funcionario";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_IDADE = "idade";
    private static final String COLUMN_CARGO = "cargo";
    private static final String COLUMN_SALARIO = "salario";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_IDADE + " INTEGER, " +
                COLUMN_CARGO + " TEXT, " +
                COLUMN_SALARIO + " REAL);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addFuncionario(String nome, String email,
                               Integer idade, String cargo, Double salario) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOME, nome);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_IDADE, idade);
        cv.put(COLUMN_CARGO, cargo);
        cv.put(COLUMN_SALARIO, salario);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);

        if(result == -1) {

            Toast.makeText(context, "Erro ao Adicionar Funcionário", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Funcionário Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT _id, nome, cargo, idade FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
