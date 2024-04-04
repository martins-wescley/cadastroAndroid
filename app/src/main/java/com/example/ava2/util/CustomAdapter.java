package com.example.ava2.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ava2.R;
import com.example.ava2.UpdateFuncionario;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList func_id, func_nome, func_cargo, func_idade;

    public CustomAdapter(Context context, ArrayList func_id, ArrayList func_nome, ArrayList func_cargo, ArrayList func_idade) {
        this.context = context;
        this.func_id = func_id;
        this.func_nome = func_nome;
        this.func_cargo = func_cargo;
        this.func_idade = func_idade;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder, final int position) {
        holder.func_id.setText(String.valueOf(func_id.get(position)));
        holder.func_nome.setText(String.valueOf(func_nome.get(position)));
        holder.func_cargo.setText(String.valueOf(func_cargo.get(position)));
        holder.func_idade.setText(String.valueOf(func_idade.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateFuncionario.class);
                intent.putExtra("id", String.valueOf(func_id.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return func_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView func_id, func_nome, func_cargo, func_idade;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            func_id = itemView.findViewById(R.id.func_id_txt);
            func_nome = itemView.findViewById(R.id.func_nome_txt);
            func_cargo = itemView.findViewById(R.id.func_cargo_txt);
            func_idade = itemView.findViewById(R.id.func_idade_txt);
            mainLayout = itemView.findViewById(R.id.meuLayout);
        }
    }
}
