package com.angel.server_spring_boot.exception;

import com.angel.server_spring_boot.dto.global.ErrorResDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResDTO> handleResponseStatusException(ResponseStatusException ex) {
        // Crear el DTO con el mensaje y detalles de la excepción
        ErrorResDTO errorResponse = new ErrorResDTO(
                ex.getReason(), 
                ex.getStatusCode().toString()
        );

        // Devolver el DTO en el cuerpo de la respuesta con el status correspondiente
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}
