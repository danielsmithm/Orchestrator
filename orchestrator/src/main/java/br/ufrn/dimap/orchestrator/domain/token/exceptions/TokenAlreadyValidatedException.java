package br.ufrn.dimap.orchestrator.domain.token.exceptions;

public class TokenAlreadyValidatedException extends Exception {
    public TokenAlreadyValidatedException(String message){
        super(message);
    }
}
