package br.com.biblioteca.repository;

import br.com.biblioteca.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select * from accounts where user = ?1", nativeQuery = true)
    Optional<Account> findByUser(String user);

    @Query(value = "select exists(select 1 from accounts a where a.user = ?1)", nativeQuery = true)
    long existByUser(String email);

    @Query(value = "select id from accounts where user = ?1", nativeQuery = true)
    Long getIdByUsername(String user);
}
