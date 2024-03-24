package br.com.biblioteca.repository;

import br.com.biblioteca.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "select * from alunos a where a.matricula = ?1", nativeQuery = true)
    Optional<Aluno> findByMatricula(String matricula);

    @Query(value = "select id from alunos where matricula = ?1", nativeQuery = true)
    Long getIdByMatricula(String matricula);

    @Query(value = "select * from alunos where account_id = ?1", nativeQuery = true)
    Optional<Aluno> findByUserId(Long id);

}
