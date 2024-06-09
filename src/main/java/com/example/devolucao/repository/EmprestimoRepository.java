package com.example.devolucao.repository;

import com.example.devolucao.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Optional<Emprestimo> findByIdLivroAndIdUsuarioAndDataEmprestimo(Long idLivro, Long idUsuario, Date dataEmprestimo);
}

