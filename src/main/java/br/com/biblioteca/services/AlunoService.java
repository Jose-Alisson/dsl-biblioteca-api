package br.com.biblioteca.services;

import br.com.biblioteca.detail.AccountDetail;
import br.com.biblioteca.dto.AlunoDTO;
import br.com.biblioteca.exception.causable.ErrDateTransfer;
import br.com.biblioteca.model.Account;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.repository.AccountRepository;
import br.com.biblioteca.repository.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    private final ModelMapper mapper = new ModelMapper();

    public AlunoDTO create(AlunoDTO alunoDTO){
        Aluno aluno = mapper.map(alunoDTO, Aluno.class);

        if(repository.isExistByMatricula(alunoDTO.getMatricula()) != 0){
            Map<String, Object> body = new HashMap<>();
            body.put("matricula", "Aluno com matricula igual");
            throw new ErrDateTransfer("", HttpStatus.BAD_REQUEST, body);
        }

        return mapper.map(repository.save(aluno), AlunoDTO.class);
    }

    public AlunoDTO createWithAccess(AlunoDTO alunoDTO) {
        Aluno aluno = mapper.map(alunoDTO, Aluno.class);

        var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if(username != null){
            aluno.setAccount(Account.builder().id(accountRepository.getIdByUsername(username)).build());

            return mapper.map(repository.save(aluno), AlunoDTO.class);
        }

        throw new ErrDateTransfer("", HttpStatus.FORBIDDEN);
    }

    public AlunoDTO update(String matricula, AlunoDTO alunoDTO){
        Optional<Aluno> alunoOpt = repository.findByMatricula(matricula);

        if(alunoOpt.isPresent()){
            Aluno aluno = alunoOpt.get();
            aluno.setNome(alunoDTO.getNome());
            aluno.setTurma(alunoDTO.getTurma());
            aluno.setTurno(alunoDTO.getTurno());
            aluno.setMatricula(alunoDTO.getMatricula());
            aluno.setAccount(aluno.getAccount());

            if(repository.isExistByMatricula(alunoDTO.getMatricula()) != 0){
                Map<String, Object> body = new HashMap<>();
                body.put("matricula", "Aluno com matricula igual");
                throw new ErrDateTransfer("", HttpStatus.BAD_REQUEST, body);
            }

            return mapper.map(repository.save(aluno), AlunoDTO.class);
        }

        throw new ErrDateTransfer("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }

    public void deleteByMatricula(String matricula){
        var opt = repository.findByMatricula(matricula);

        if(opt.isPresent()){
            repository.deleteById(opt.get().getId());
            return;
        }

        throw new ErrDateTransfer("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }

    public AlunoDTO getByAccount(){
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var opt = repository.findByUserId(accountRepository.getIdByUsername("" + username));

        if(opt.isPresent()){
            return mapper.map(opt.get(), AlunoDTO.class);
        }
        throw new ErrDateTransfer("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }

    public List<AlunoDTO> getAll(){
        return repository.findAll().stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).toList();
    }

    public Map<String, List<AlunoDTO>> ordernarTurma(List<AlunoDTO> dtos){
        Map<String, List<AlunoDTO>> main = new HashMap<>();

        dtos.forEach(dto -> {
            if (!main.containsKey(dto.getTurma())) {
                main.put(dto.getTurma(), new ArrayList<>());
            }
        });

        main.forEach((key,value) -> {
            value.addAll(dtos.stream().filter(dto -> dto.getTurma().equalsIgnoreCase(key)).toList());
        });

        return main;
    }

    public AlunoDTO getByMatricula(String matricula) {
        Optional<Aluno> alunoOpt = repository.findByMatricula(matricula);

        if(alunoOpt.isPresent()){
            return mapper.map(alunoOpt.get(), AlunoDTO.class);
        }

        throw new ErrDateTransfer("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }
}
