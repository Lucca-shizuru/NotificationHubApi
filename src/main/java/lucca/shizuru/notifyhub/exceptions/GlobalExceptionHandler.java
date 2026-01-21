package lucca.shizuru.notifyhub.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleEnumErrors(HttpMessageNotReadableException ex) {
        System.err.println("Erro na leitura do JSON: " + ex.getMessage());

        Map<String, String> error = new HashMap<>();

        if (ex.getMessage() != null && ex.getMessage().contains("NotificationChannel")) {
            error.put("error", "Canal de envio não suportado. Use EMAIL ou SMS.");
        } else {
            error.put("error", "Erro na formatação do JSON enviado.");
        }

        return ResponseEntity.badRequest().body(error);
    }
}
