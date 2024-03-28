package br.com.biblioteca.repository;

import br.com.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
