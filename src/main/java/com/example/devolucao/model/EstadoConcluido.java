package com.example.devolucao.model;

public class EstadoConcluido extends EstadoDevolucao {

    public EstadoConcluido(Emprestimo emprestimo) {
        super(emprestimo);
    }

    @Override
    public String descrever() {
        return "Devolução concluída.";
    }

    @Override
    public void tratar() {
    
    }
}