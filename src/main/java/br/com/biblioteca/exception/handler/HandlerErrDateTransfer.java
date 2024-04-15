package br.com.biblioteca.exception.handler;

import br.com.biblioteca.exception.causable.ErrDateTransfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerErrDateTransfer {

    @ExceptionHandler(ErrDateTransfer.class)
    public ResponseEntity<?> handle(ErrDateTransfer err){
        Map<String, Object> map = err.getBody();
        
        if(map == null){
            map = new HashMap<>();
            map.put("error", err.getMessage());
        }
        return ResponseEntity.status(err.getStatus()).body(map);
    }
}