package com.example.devolucao.model;

public abstract class EstadoDevolucao {
    protected Emprestimo emprestimo;

    public EstadoDevolucao(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public abstract String descrever();
    public abstract void tratar();
}
