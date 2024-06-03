package com.example.devolucao.controller;

import com.example.devolucao.model.Emprestimo;
import com.example.devolucao.repository.EmprestimoRepository;
import com.example.devolucao.service.DevolucaoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private DevolucaoFacade devolucaoFacade;

    @GetMapping
    public String getAllEmprestimos(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        
        if (query != null && !query.isEmpty()) {
            emprestimos = emprestimos.stream()
                    .filter(e -> e.getIdUsuario().toString().contains(query) || 
                                 e.getIdLivro().toString().contains(query))
                    .collect(Collectors.toList());
        }

        List<Emprestimo> emAberto = emprestimos.stream()
                .filter(e -> !e.getStatus().equals("Devolvido"))
                .collect(Collectors.toList());
        List<Emprestimo> concluidos = emprestimos.stream()
                .filter(e -> e.getStatus().equals("Devolvido"))
                .collect(Collectors.toList());
        
        model.addAttribute("emAberto", emAberto);
        model.addAttribute("concluidos", concluidos);
        model.addAttribute("query", query);
        return "emprestimos";
    }

    @PostMapping("/devolver/{id}")
    public String devolverLivro(@PathVariable Long id) {
        devolucaoFacade.devolverLivro(id);
        return "redirect:/emprestimos";
    }

    @GetMapping("/{id}")
    public String getEmprestimoById(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        model.addAttribute("emprestimo", emprestimo);
        return "detalhesEmprestimo";
    }

    @PostMapping("/devolver-detalhe/{id}")
    public String devolverEmprestimoDetalhe(@PathVariable Long id) {
        devolucaoFacade.devolverLivro(id);
        return "redirect:/emprestimos";
    }
}
