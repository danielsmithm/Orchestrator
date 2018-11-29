package br.ufrn.dimap.orchestrator.domain.token;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
