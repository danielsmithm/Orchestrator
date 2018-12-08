package br.ufrn.dimap.orchestrator.domain.token.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class InvalidTokenException extends ValidationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
