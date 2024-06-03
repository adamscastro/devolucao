package com.example.devolucao.model;

public class Devolvido implements EstadoEmprestimo {
    @Override
    public void proximoEstado(Emprestimo emprestimo) {
        // No further state from Devolvido, could throw an exception or log a message
    }

    @Override
    public String getNomeEstado() {
        return "Devolvido";
    }
}
