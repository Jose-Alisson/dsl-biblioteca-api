package br.com.biblioteca.dto;

import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Livro;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmprestimoDTO {

    public Long id;

    @NotNull(message = "O aluno não pode ser nulo, insira pelo-menos a matricula")
    public Aluno aluno;

    @NotNull(message = "O titulo não pode ser nulo")
    @NotBlank(message = "O titulo não pode ser vazio")
    public String titulo;

    @NotNull(message = "A lista de livros não pode ser nula")
    public List<Livro> livros;

    public LocalDate dataCriacao;

    @NotNull(message = "A data de devolução não pode ser nula")
    public LocalDate dataDevolucao;

    public LocalDate dataDevolvida;
}
