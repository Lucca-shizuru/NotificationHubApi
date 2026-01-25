package lucca.shizuru.notifyhub.dto;

import java.time.Instant;
import java.util.List;

public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<ValidationError> fieldErrors
) {
    public record ValidationError(String field, String message){}

}
