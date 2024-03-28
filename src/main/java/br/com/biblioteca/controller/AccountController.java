package br.com.biblioteca.controller;

import br.com.biblioteca.model.Account;
import br.com.biblioteca.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("user") String user, @RequestParam("password") String password){
        return ResponseEntity.ok(service.login(user, password));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Account account){
        return ResponseEntity.ok(service.create(account));
    }
}
