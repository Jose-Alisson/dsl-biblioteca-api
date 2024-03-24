package br.com.biblioteca.controller;

import br.com.biblioteca.dto.AlunoDTO;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AlunoDTO alunoDTO){
        return ResponseEntity.ok(service.create(alunoDTO));
    }

    @PostMapping("/createWithAccess")
    public ResponseEntity<?> createAlunoAccess(@RequestBody AlunoDTO alunoDTO){
        return ResponseEntity.ok(service.createWithAccess(alunoDTO));
    }

    @PutMapping("/{matricula}/update")
    public ResponseEntity<?> update(@PathVariable("matricula") String matricula,@RequestBody AlunoDTO alunoDTO){
        return ResponseEntity.ok(service.update(matricula,alunoDTO));
    }

    @DeleteMapping("/{matricula}/delete")
    public ResponseEntity<?> delete(@PathVariable("matricula") String matricula){
        service.deleteByMatricula(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{matricula}/")
    public ResponseEntity<?> getByMatricula(@PathVariable("matricula") String matricula){
        return ResponseEntity.ok(service.getByMatricula(matricula));
    }

    @GetMapping("/byAccess")
    public ResponseEntity<?> getByAccount(){
        return ResponseEntity.ok(service.getByAccount());
    }

    /*@PreAuthorize("hasAuthority('ADMIN')")*/
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.ordernarTurma(service.getAll()));
    }
}
