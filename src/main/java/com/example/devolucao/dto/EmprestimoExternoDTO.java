package com.example.devolucao.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmprestimoExternoDTO {
    private Long id_livro;

    private String titulo_livro;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_emprestimo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_devolucao;

    private String emprestado_para;

    private Long id_usuario;

    // Getters e Setters
    public Long getId_livro() {
        return id_livro;
    }

    public void setId_livro(Long id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitulo_livro() {
        return titulo_livro;
    }

    public void setTitulo_livro(String titulo_livro) {
        this.titulo_livro = titulo_livro;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getEmprestado_para() {
        return emprestado_para;
    }

    public void setEmprestado_para(String emprestado_para) {
        this.emprestado_para = emprestado_para;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
