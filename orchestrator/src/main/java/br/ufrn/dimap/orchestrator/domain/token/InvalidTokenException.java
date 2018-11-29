package br.ufrn.dimap.orchestrator.domain.token;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message) {
        super(message);
    }
}
