package br.ufrn.dimap.orchestrator.domain.token.exceptions;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
