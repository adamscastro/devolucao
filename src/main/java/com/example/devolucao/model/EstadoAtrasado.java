package com.example.devolucao.model;

public class EstadoAtrasado extends EstadoDevolucao {

    public EstadoAtrasado(Emprestimo emprestimo) {
        super(emprestimo);
    }

    @Override
    public String descrever() {
        int diasAtraso = emprestimo.getDiasAtraso();
        return "Devolução atrasada em " + diasAtraso + " dias.";
    }

    @Override
    public void tratar() {
        if (emprestimo.isMultaPaga()) {
            emprestimo.setEstado(new EstadoConcluido(emprestimo));
        }
    }
}
