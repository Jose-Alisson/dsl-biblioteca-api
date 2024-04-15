package br.com.biblioteca.exception.causable;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ErrDateTransfer extends RuntimeException {

    private final HttpStatus status;

    private final Map<String, Object> body;

    public ErrDateTransfer(String message, HttpStatus status){
        super(message);
        this.status = status;
        this.body = new HashMap<>();
    }
    public ErrDateTransfer(String message, HttpStatus status, Map<String, Object> body){
        super(message);
        this.body = body;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
