package com.example.devolucao.model;

public interface EstadoEmprestimo {
    void proximoEstado(Emprestimo emprestimo);
    String getNomeEstado();
}