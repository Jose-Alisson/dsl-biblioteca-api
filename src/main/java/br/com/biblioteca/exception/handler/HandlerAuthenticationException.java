package br.com.biblioteca.exception.handler;

import br.com.biblioteca.exception.causable.ErrDateTransfer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerAuthenticationException {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handle(AuthenticationException err){
        return ResponseEntity.status(401).body(err.getMessage());
    }
}
