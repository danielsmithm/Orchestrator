package br.ufrn.dimap.orchestrator.domain.application.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ApplicationNotFoundException extends ValidationException {

	public ApplicationNotFoundException(String message) {
		super(message);
	}
	
}
