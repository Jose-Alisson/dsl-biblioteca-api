package br.com.biblioteca.dto;

import br.com.biblioteca.enums.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {
    Long id;

    @NotNull(message = "O usuário não pode ser nulo")
    @NotBlank(message = "O usuário não pode ser vazio")
    String user;

    TypeAccount typeAccount;
}
