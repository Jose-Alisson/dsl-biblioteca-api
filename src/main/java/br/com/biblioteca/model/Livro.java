package br.com.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "livros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @Id
    /*@NotNull(message = "O código não pode ser nulo")
    @NotBlank(message = "O código não pode ser branco")*/
    private String codigo;

   /* @NotNull(message = "O titulo não pode ser nulo")
    @NotBlank(message = "O titulo não pode ser branco")*/
    private String titulo;

    /*@NotNull(message = "O gênero não pode ser nulo")
    @NotBlank(message = "O gênero não pode ser branco")*/
    private String genero;
}
