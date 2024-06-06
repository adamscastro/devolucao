package com.example.devolucao.model;

public class EstadoPendente extends EstadoDevolucao {

    public EstadoPendente(Emprestimo emprestimo) {
        super(emprestimo);
    }

    @Override
    public String descrever() {
        int diasRestantes = emprestimo.getDiasRestantes();
        if (diasRestantes > 0) {
            return "Faltam " + diasRestantes + " dias para a devolução.";
        } else if (diasRestantes == 0) {
            return "Hoje é o último dia para a devolução.";
        } else {
            emprestimo.setEstado(new EstadoAtrasado(emprestimo));
            return emprestimo.getEstado().descrever();
        }
    }

    @Override
    public void tratar() {
        int diasRestantes = emprestimo.getDiasRestantes();
        if (diasRestantes < 0) {
            emprestimo.setEstado(new EstadoAtrasado(emprestimo));
        } else if (diasRestantes == 0) {
            emprestimo.setEstado(new EstadoConcluido(emprestimo));
            emprestimo.tratar();
        } else {
            emprestimo.setEstado(new EstadoConcluido(emprestimo));
            emprestimo.tratar();
        }
    }
}
