package br.com.biblioteca.repository;

import br.com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query(value = "select * from livros l where l.codigo = ?1", nativeQuery = true)
    Optional<Livro> findByCodigo(String codigo);

    @Query(value = "select id from livros where codigo = ?1", nativeQuery = true)
    Long getIdByCodigo(String codigo);
}
