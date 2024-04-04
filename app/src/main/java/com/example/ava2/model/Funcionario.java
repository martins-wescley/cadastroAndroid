package com.example.ava2.model;

public class Funcionario {
    private String nome;
    private String email;
    private Integer idade;
    private String cargo;
    private Double salario;

    public Funcionario() {
    }

    public Funcionario(String nome, String email, Integer idade, String cargo, Double salario) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
