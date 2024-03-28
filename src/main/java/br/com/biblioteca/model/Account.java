package br.com.biblioteca.model;

import br.com.biblioteca.enums.TypeAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotNull(message = "O usuário não pode ser nulo")
    @NotBlank(message = "O usuário não pode ser vazio")*/
    @Column(name = "user", unique = true)
    private String user;

    /*@NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser vazia")
    @Size(min = 6, max = 12, message = "A senha deve ter pelo menos 6 caracteres e deve ter no maximo 12 caracteres ")*/
    private String password;

    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;
}
