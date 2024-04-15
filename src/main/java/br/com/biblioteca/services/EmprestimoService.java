package br.com.biblioteca.services;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.exception.causable.ErrDateTransfer;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.AlunoRepository;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LivroRepository livroRepository;

    private final ModelMapper mapper = new ModelMapper();

    public EmprestimoDTO create(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = mapper.map(emprestimoDTO, Emprestimo.class);
        emprestimo.setDataCriacao(LocalDate.now());

        if (emprestimo.getAluno() != null) {
            Optional<Aluno> aluno = alunoRepository.findByMatricula(emprestimoDTO.getAluno().getMatricula());

            if(aluno.isPresent()){
                emprestimo.setAluno(aluno.get());
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("aluno", "Buscado por matricula: Aluno não encontrado");
                throw new ErrDateTransfer("", HttpStatus.BAD_REQUEST, map);
            }
        }

        emprestimo.setLivros(livroRepository.findAllByCodigo(emprestimoDTO.getLivros().stream().map(Livro::getCodigo).toList()));

        return mapper.map(repository.save(emprestimo), EmprestimoDTO.class);
    }

    public EmprestimoDTO update(Long id, EmprestimoDTO emprestimoDTO) {
        Optional<Emprestimo> emprestimoOpt = repository.findById(id);

        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimo = emprestimoOpt.get();
            emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
            emprestimo.setTitulo(emprestimoDTO.getTitulo());

            if (emprestimo.getAluno() != null) {
               Optional<Aluno> aluno = alunoRepository.findByMatricula(emprestimoDTO.getAluno().getMatricula());

                if(aluno.isPresent()){
                    emprestimo.setAluno(aluno.get());
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("aluno", "Buscado por matricula: Aluno não encontrado");
                    throw new ErrDateTransfer("", HttpStatus.BAD_REQUEST, map);
                }
            }

            //emprestimo.setLivros(livroRepository.findAllById(livroRepository. emprestimoDTO.getLivros().stream().map(Livro::getCodigo).toList()));
            emprestimo.setLivros(livroRepository.findAllByCodigo(emprestimoDTO.getLivros().stream().map(Livro::getCodigo).toList()));

            emprestimo.setDataDevolvida(emprestimoDTO.getDataDevolvida());

            return mapper.map(repository.save(emprestimo), EmprestimoDTO.class);
        }

        throw new ErrDateTransfer("", HttpStatus.NOT_FOUND);
    }

    public void delete(Long id) {
        Optional<Emprestimo> emprestimoOpt = repository.findById(id);

        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimo = emprestimoOpt.get();
            emprestimo.setAluno(null);
            emprestimo.setLivros(null);

            repository.delete(repository.save(emprestimo));
            return;
        }
        throw new ErrDateTransfer("", HttpStatus.NOT_FOUND);
    }

    public List<EmprestimoDTO> getAll() {
        return repository.findAll().stream().map(emprestimo -> mapper.map(emprestimo, EmprestimoDTO.class)).toList();
    }

    public void devolver(Long id) {
        Optional<Emprestimo> emprestimoOpt = repository.findById(id);

        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimo = emprestimoOpt.get();
            emprestimo.setDataDevolvida(LocalDate.now());
            repository.save(emprestimo);
            return;
        }

        throw new ErrDateTransfer("", HttpStatus.NOT_FOUND);
    }

    public Map<String, Map<String, List<EmprestimoDTO>>> ordenar(List<EmprestimoDTO> listDto) {
        Map<String, Map<String, List<EmprestimoDTO>>> main = new HashMap<>();

        listDto.forEach(dto -> {
            if(dto.getAluno() != null) {
                if (!main.containsKey(dto.getAluno().getTurma())) {
                    main.put(dto.getAluno().getTurma(), new HashMap<>());
                }
            }
        });

        main.forEach((turma, valor) -> {
            listDto.stream().filter(emp -> emp.getAluno().getTurma().equals(turma)).forEach(dto -> {
                if (!valor.containsKey(dto.getAluno().getNome())) {
                    valor.put(dto.getAluno().getNome(), new ArrayList<>());
                }
            });

            valor.forEach((aluno, valor_) -> {
                valor_.addAll(listDto.stream().filter(dto -> dto.getAluno().getTurma().equalsIgnoreCase(turma) && dto.getAluno().getNome().equals(aluno)).toList());
            });
        });
        return main;
    }
}
