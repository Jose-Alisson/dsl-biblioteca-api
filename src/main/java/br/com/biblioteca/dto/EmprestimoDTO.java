package br.com.biblioteca.dto;

import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Livro;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmprestimoDTO {

    public Long id;
    public Aluno aluno;
    public String titulo;
    public List<Livro> livros;
    public LocalDate dataCriacao;
    public LocalDate dataDevolucao;
    public LocalDate dataDevolvida;
}
