package br.com.biblioteca.repository;

import br.com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LivroRepository extends JpaRepository<Livro, String> {
    @Query(value = "select * from livros l where l.codigo = ?1", nativeQuery = true)
    Optional<Livro> findByCodigo(String codigo);

    @Query(value = "select id from livros where codigo = ?1", nativeQuery = true)
    Long getIdByCodigo(String codigo);
}
