package com.devsuperior.demo.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.demo.dto.CustomError;
import com.devsuperior.demo.dto.ValidationError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(Instant.now(), status.value(), "Erro na validação!",
				request.getRequestURI());
		for (FieldError err : exception.getFieldErrors()) {
			error.addError(err.getField(), err.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}
}
