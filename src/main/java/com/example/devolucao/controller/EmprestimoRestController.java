package com.example.devolucao.controller;

import com.example.devolucao.model.Emprestimo;
import com.example.devolucao.service.DevolucaoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoRestController {

    @Autowired
    private DevolucaoFacade devolucaoFacade;

    @GetMapping
    public List<Emprestimo> getAllEmprestimos() {
        return devolucaoFacade.getAllEmprestimos();
    }
}
