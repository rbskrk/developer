package com.rbs.os.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rbs.os.service.exception.DataIntegratyViolationException;
import com.rbs.os.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResoucesExpectionHandler {

	// Manipulador para excessão Objeto não encontrado (ObjectNotFoundException)
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	// Manipulador para excessão Obj.CPF ja cadastrado na base de dados (DataIntegratyViolationException)
	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError> objectNotFoundException(DataIntegratyViolationException e) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	// Manipulador para excessão campos nulos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> objectNotFoundException(MethodArgumentNotValidException e) {

		ValidationError error = new ValidationError(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos!");
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErros(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
