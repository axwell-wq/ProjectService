package faang.school.projectservice.handler;

import faang.school.projectservice.exception.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        log.error("Entity not found for request {}: {}", request.getDescription(false), exception.getMessage(), exception);
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(StringUtils.defaultIfEmpty(exception.getMessage(), "Entity not found"))
                .build();
    }

    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataValidationException(DataValidationException exception, WebRequest request) {
        log.error("Validation error for request {}: {}", request.getDescription(false), exception.getMessage(), exception);
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(StringUtils.defaultIfEmpty(exception.getMessage(), "Validation error"))
                .build();
    }
}
