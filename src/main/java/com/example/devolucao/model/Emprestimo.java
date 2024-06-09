package com.example.devolucao.model;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = true)
    private Long idUsuario;

    @Column(name = "id_livro", nullable = true)
    private Long idLivro;

    @Column(name = "data_emprestimo", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;

    @Column(name = "data_prevista_devolucao", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaDevolucao;

    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "multa_paga", nullable = false)
    private boolean multaPaga = false;

    @JsonIgnore
    @Transient
    private EstadoDevolucao estado;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void defineEstado() {
        switch (this.status) {
            case "Concluido":
                this.estado = new EstadoConcluido(this);
                break;
            case "Atrasado":
                this.estado = new EstadoAtrasado(this);
                break;
            default:
                this.estado = new EstadoPendente(this);
                break;
        }
    }

    public void setEstado(EstadoDevolucao estado) {
        this.estado = estado;
        this.status = estado.getClass().getSimpleName().replace("Estado", "");
        if (estado instanceof EstadoConcluido) {
            this.dataDevolucao = new Date();
        }
    }

    public String descrever() {
        return estado.descrever();
    }

    public void tratar() {
        estado.tratar();
    }

    public int getDiasRestantes() {
        LocalDate dataPrevista = convertToLocalDateViaSqlDate(dataPrevistaDevolucao);
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), dataPrevista);
    }

    public int getDiasAtraso() {
        LocalDate dataPrevista = convertToLocalDateViaSqlDate(dataPrevistaDevolucao);
        return (int) ChronoUnit.DAYS.between(dataPrevista, LocalDate.now());
    }

    public boolean isMultaPaga() {
        return multaPaga;
    }

    public void pagarMulta() {
        this.multaPaga = true;
        this.tratar();
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

    public EstadoDevolucao getEstado() {
        return estado;
    }

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public String getMensagensEstado() {
        return estado.descrever();
    }
}