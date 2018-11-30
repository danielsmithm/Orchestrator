package br.ufrn.dimap.orchestrator.domain.token.exceptions;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message) {
        super(message);
    }
}
