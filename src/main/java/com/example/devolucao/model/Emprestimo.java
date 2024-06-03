package com.example.devolucao.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_livro", nullable = false)
    private Long idLivro;

    @Column(name = "data_emprestimo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;

    @Column(name = "data_prevista_devolucao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaDevolucao;

    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;

    @Column(name = "status", nullable = false)
    private String status;

    @Transient
    private EstadoEmprestimo estado;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void defineEstado() {
        if ("Devolvido".equals(this.status)) {
            this.estado = new Devolvido();
        } else {
            this.estado = new Emprestado();
        }
    }

    public void setEstado(EstadoEmprestimo estado) {
        this.estado = estado;
        this.status = estado.getNomeEstado();
    }

    public void proximoEstado() {
        if (estado != null) {
            estado.proximoEstado(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EstadoEmprestimo getEstado() {
        return estado;
    }

    
}