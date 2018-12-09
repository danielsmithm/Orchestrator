package br.ufrn.dimap.orchestrator.domain.token.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class TokenAlreadyValidatedException extends ValidationException {
    public TokenAlreadyValidatedException(String message){
        super(message);
    }
}
