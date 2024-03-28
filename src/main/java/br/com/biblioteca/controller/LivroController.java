package br.com.biblioteca.controller;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.repository.LivroRepository;
import br.com.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(service.create(livroDTO));
    }

    @PutMapping("/{codigo}/update")
    public ResponseEntity<?> update(@PathVariable("codigo") String codigo,@Valid @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(service.update(codigo, livroDTO));
    }

    @DeleteMapping("/{codigo}/delete")
    public ResponseEntity<?> delete(@PathVariable("codigo") String codigo) {
        service.delete(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getLivros());
    }
}
