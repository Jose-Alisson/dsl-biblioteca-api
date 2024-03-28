package br.com.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "emprestimos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "O aluno não pode ser nulo, insira pelo-menos a matricula")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    //@NotNull(message = "O titulo não pode ser nulo")
    //@NotBlank(message = "O titulo não pode ser vazio")
    private String titulo;

    //@NotNull(message = "A lista de livros não pode ser nula")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Livro> livros;

    private LocalDate dataCriacao;

    //@NotNull(message = "A data de devolução não pode ser nula")
    private LocalDate dataDevolucao;

    private LocalDate dataDevolvida;
}
