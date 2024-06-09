package com.example.devolucao.service;

import com.example.devolucao.dto.EmprestimoExternoDTO;
import com.example.devolucao.model.Emprestimo;
import com.example.devolucao.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DevolucaoFacade {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String EMPRESTIMO_API_URL = "https://emprestimo-django-app.onrender.com/emprestimos_json";

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

    // Método para consumir dados do serviço Django e salvar no banco de dados local
    public List<Emprestimo> fetchAndSaveExternalEmprestimos() {
        EmprestimoExternoDTO[] emprestimosDTO = restTemplate.getForObject(EMPRESTIMO_API_URL, EmprestimoExternoDTO[].class);
        List<Emprestimo> emprestimos = Arrays.stream(emprestimosDTO)
                .map(this::convertToEmprestimo)
                .filter(this::isNewEmprestimo) // Verifica se o empréstimo já existe
                .collect(Collectors.toList());
        emprestimos.forEach(this::saveEmprestimo); // Salva cada novo empréstimo no banco de dados
        return emprestimos;
    }

    private Emprestimo convertToEmprestimo(EmprestimoExternoDTO dto) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(null);
        emprestimo.setIdUsuario(dto.getId_usuario());
        emprestimo.setIdLivro(dto.getId_livro());
        emprestimo.setDataEmprestimo(convertToDate(dto.getData_emprestimo()));
        emprestimo.setDataPrevistaDevolucao(convertToDate(dto.getData_devolucao()));
        emprestimo.setDataDevolucao(null);
        emprestimo.setStatus("Pendente");
        return emprestimo;
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private boolean isNewEmprestimo(Emprestimo emprestimo) {
        Optional<Emprestimo> existingEmprestimo = emprestimoRepository.findByIdLivroAndIdUsuarioAndDataEmprestimo(
                emprestimo.getIdLivro(),
                emprestimo.getIdUsuario(),
                emprestimo.getDataEmprestimo()
        );
        return existingEmprestimo.isEmpty();
    }
}
