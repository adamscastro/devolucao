package com.example.devolucao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.devolucao.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}