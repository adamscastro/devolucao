package com.example.devolucao.controller;

import com.example.devolucao.service.DevolucaoFacade;
import com.example.devolucao.model.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private DevolucaoFacade devolucaoFacade;

    @GetMapping
    public String getAllEmprestimos(Model model) {
        List<Emprestimo> emprestimos = devolucaoFacade.getAllEmprestimos();
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimos";
    }

    @PostMapping("/processar/{id}")
    public String processarEmprestimo(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = devolucaoFacade.getEmprestimoById(id);
        devolucaoFacade.processarEmprestimo(id);
        model.addAttribute("mensagens", emprestimo.getMensagensEstado());
        model.addAttribute("emprestimo", emprestimo);
        return "detalhes_emprestimo";
    }

    @PostMapping("/pagarMulta/{id}")
    public String pagarMulta(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = devolucaoFacade.getEmprestimoById(id);
        devolucaoFacade.pagarMulta(id);
        model.addAttribute("mensagens", emprestimo.getMensagensEstado());
        model.addAttribute("emprestimo", emprestimo);
        return "detalhes_emprestimo";
    }

    @GetMapping("/detalhes/{id}")
    public String getEmprestimoDetalhes(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = devolucaoFacade.getEmprestimoById(id);
        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("mensagens", emprestimo.getMensagensEstado());
        return "detalhes_emprestimo";
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<List<Emprestimo>> getAllEmprestimosJson() {
        List<Emprestimo> emprestimos = devolucaoFacade.getAllEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
}
