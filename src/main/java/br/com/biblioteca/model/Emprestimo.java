package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "emprestimos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    private String titulo;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Livro> livros;

    private LocalDate dataCriacao;

    private LocalDate dataDevolucao;

    private LocalDate dataDevolvida;
}
