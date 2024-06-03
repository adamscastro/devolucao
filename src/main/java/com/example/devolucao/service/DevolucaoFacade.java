package com.example.devolucao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.devolucao.model.Emprestimo;
import com.example.devolucao.repository.EmprestimoRepository;
import java.util.Date;

@Service
public class DevolucaoFacade {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public void devolverLivro(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        emprestimo.setDataDevolucao(new Date());
        emprestimo.proximoEstado();
        emprestimoRepository.save(emprestimo);
    }
}
