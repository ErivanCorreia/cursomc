package com.erivan.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	public List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}
	
	public List<FieldMessage> getErros() {
		return erros;
	}
	public void addError(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}

	
}
