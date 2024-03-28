package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoDTO {
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser nulo")
    public String nome;

    @NotBlank(message = "A turma não pode ser vazio")
    @NotNull(message = "A turma não pode ser nulo")
    public String turma;

    @NotBlank(message = "A matricula não pode ser vazio")
    @NotNull(message = "A matricula não pode ser nulo")
    public String matricula;

    @NotBlank(message = "O turno não pode ser vazio")
    @NotNull(message = "O turno não pode ser nulo")
    public String turno;
}
