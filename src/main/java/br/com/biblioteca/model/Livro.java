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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;

    private String editora;

    private String volume;

    private String classificacao;

    //@Id
    /*@NotNull(message = "O código não pode ser nulo")
    @NotBlank(message = "O código não pode ser branco")*/
    @Column(unique = true)
    private String codigo;

   /* @NotNull(message = "O titulo não pode ser nulo")
    @NotBlank(message = "O titulo não pode ser branco")*/
    private String titulo;

    private String quantidade;

    /*@NotNull(message = "O gênero não pode ser nulo")
    @NotBlank(message = "O gênero não pode ser branco")*/
    private String genero;

    private String observacao;
}
