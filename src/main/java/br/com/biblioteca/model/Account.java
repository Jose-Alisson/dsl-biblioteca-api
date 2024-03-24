package br.com.biblioteca.model;

import br.com.biblioteca.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user", unique = true)
    private String user;

    private String password;

    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;

}
