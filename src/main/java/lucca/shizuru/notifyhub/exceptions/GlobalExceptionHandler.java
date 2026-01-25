package lucca.shizuru.notifyhub.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lucca.shizuru.notifyhub.dto.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> new StandardError.ValidationError(f.getField(), f.getDefaultMessage()))
                .toList();

        StandardError err = new StandardError(
                Instant.now(), HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
                "Verifique os campos enviados", request.getRequestURI(), fieldErrors);

        log.warn("⚠️ Falha de validação em {}: {}", request.getRequestURI(), fieldErrors);
        return ResponseEntity.badRequest().body(err);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleEnumErrors(HttpMessageNotReadableException ex,  HttpServletRequest request) {
        String userMassage = "erro na formatação de JSON.";

        if (ex.getMessage() != null && ex.getMessage().contains("NotificationChannel")) {
            userMassage = "Canal de envio não suportado. Use EMAIL ou SMS.";
        }
        StandardError err = new StandardError(
                Instant.now(), HttpStatus.BAD_REQUEST.value(), "JSON Inválido",
                userMassage, request.getRequestURI(), null);

        log.error("Erro na leitura do JSON em {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.badRequest().body(err);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleBusinessError(IllegalArgumentException ex, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(), HttpStatus.BAD_REQUEST.value(), "Regra de Negócio",
                ex.getMessage(), request.getRequestURI(), null);

        log.error("Violação de regra: {}", ex.getMessage());


        return ResponseEntity.badRequest().body(err);
    }
}
