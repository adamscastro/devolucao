package com.example.devolucao.model;

public class Emprestado implements EstadoEmprestimo {
    @Override
    public void proximoEstado(Emprestimo emprestimo) {
        emprestimo.setEstado(new Devolvido());
    }

    @Override
    public String getNomeEstado() {
        return "Emprestado";
    }
}
