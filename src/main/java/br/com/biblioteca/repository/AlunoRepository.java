package br.com.biblioteca.repository;

import br.com.biblioteca.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AlunoRepository extends JpaRepository<Aluno, String> {
    @Query(value = "select * from alunos a where a.matricula = ?1", nativeQuery = true)
    Optional<Aluno> findByMatricula(String matricula);

    @Query(value = "select id from alunos where matricula = ?1", nativeQuery = true)
    Long getIdByMatricula(String matricula);

    @Query(value = "select * from alunos where account_id = ?1", nativeQuery = true)
    Optional<Aluno> findByUserId(Long id);

}
