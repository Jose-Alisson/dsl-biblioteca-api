package br.com.biblioteca.controller;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmprestimoDTO emprestimoDTO){
        return ResponseEntity.ok(service.create(emprestimoDTO));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody EmprestimoDTO emprestimoDTO){
        return ResponseEntity.ok(service.update(id, emprestimoDTO));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/devolver")
    public void devolver(@PathVariable("id") Long id){
        service.devolver(id);
    }


    @GetMapping("/ordenar")
    public ResponseEntity<?> getAllOrdenarPorTurma(){
        return ResponseEntity.ok(service.ordenar(service.getAll()));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
