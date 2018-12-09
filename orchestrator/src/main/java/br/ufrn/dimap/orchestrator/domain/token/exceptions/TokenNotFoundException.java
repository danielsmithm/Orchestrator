package br.ufrn.dimap.orchestrator.domain.token.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class TokenNotFoundException extends ValidationException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
