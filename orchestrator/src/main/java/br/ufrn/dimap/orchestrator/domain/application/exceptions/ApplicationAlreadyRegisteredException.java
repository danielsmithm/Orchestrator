package br.ufrn.dimap.orchestrator.domain.application.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ApplicationAlreadyRegisteredException extends ValidationException {
    public ApplicationAlreadyRegisteredException(String message) {
        super(message);
    }
}
