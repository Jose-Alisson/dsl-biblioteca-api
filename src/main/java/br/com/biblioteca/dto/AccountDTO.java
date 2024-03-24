package br.com.biblioteca.dto;

import br.com.biblioteca.enums.TypeAccount;
import lombok.Data;

@Data
public class AccountDTO {
    Long id;
    String user;
    TypeAccount typeAccount;
}
