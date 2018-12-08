package br.ufrn.dimap.orchestrator.domain.token.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class InvalidServiceException extends ValidationException {
	public InvalidServiceException(String message) {
		super(message);
	}
}
