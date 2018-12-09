package br.ufrn.dimap.orchestrator.domain.application.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class PasswordNotInformedException extends ValidationException {
    public PasswordNotInformedException(String message) {
        super(message);
    }
}
