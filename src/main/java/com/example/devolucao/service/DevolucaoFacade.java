package com.example.devolucao.service;

import com.example.devolucao.model.Emprestimo;
import com.example.devolucao.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevolucaoFacade {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> getAllEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo getEmprestimoById(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan Id:" + id));
    }

    public void processarEmprestimo(Long id) {
        Emprestimo emprestimo = getEmprestimoById(id);
        emprestimo.tratar();
        saveEmprestimo(emprestimo);
    }

    public void pagarMulta(Long id) {
        Emprestimo emprestimo = getEmprestimoById(id);
        emprestimo.pagarMulta();
        saveEmprestimo(emprestimo);
    }

    public void saveEmprestimo(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }
}
