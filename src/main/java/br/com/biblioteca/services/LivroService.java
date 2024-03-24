package br.com.biblioteca.services;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.exception.causable.ErrDateTransfer;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public LivroDTO create(LivroDTO livroDTO){
        return mapper.map(repository.save(mapper.map(livroDTO, Livro.class)), LivroDTO.class);
    }

    public LivroDTO update(String codigo, LivroDTO livroDTO){
        var livroOpt = repository.findByCodigo(codigo);

        if(livroOpt.isPresent()){
            var livro = livroOpt.get();
            livro.setTitulo(livroDTO.getTitulo());
            livro.setGenero(livroDTO.getGenero());
            livro.setCodigo(livroDTO.getCodigo());

            return mapper.map(repository.save(livro), LivroDTO.class);
        }

        throw new ErrDateTransfer("", HttpStatus.NOT_FOUND);
    }

    public void delete(String codigo){
        Optional<Livro> livroOpt = repository.findByCodigo(codigo);

        if(livroOpt.isPresent()){
            repository.delete(livroOpt.get());
            return;
        }

        throw new ErrDateTransfer("", HttpStatus.NOT_FOUND);
    }

    public List<LivroDTO> getLivros(){
        return repository.findAll().stream().map(livro -> mapper.map(livro,LivroDTO.class)).toList();
    }
}
