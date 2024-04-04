package br.com.biblioteca.controller;

import br.com.biblioteca.model.Account;
import br.com.biblioteca.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("user") String user, @RequestParam("password") String password){
        var map = new HashMap<String, String>();
        map.put("access", service.login(user, password));
        return ResponseEntity.ok(map);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Account account){
        return ResponseEntity.ok(service.create(account));
    }
}
