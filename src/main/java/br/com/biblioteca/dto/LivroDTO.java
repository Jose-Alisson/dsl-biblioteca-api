package br.com.biblioteca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroDTO {

    @NotNull(message = "O titulo não pode ser nulo")
    @NotBlank(message = "O titulo não pode ser branco")
    public String titulo;

    @NotNull(message = "O gênero não pode ser nulo")
    @NotBlank(message = "O gênero não pode ser branco")
    public String genero;

    @NotNull(message = "O código não pode ser nulo")
    @NotBlank(message = "O código não pode ser branco")
    public String codigo;
}
