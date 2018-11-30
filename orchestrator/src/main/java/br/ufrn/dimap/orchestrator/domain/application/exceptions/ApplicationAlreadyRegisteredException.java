package br.ufrn.dimap.orchestrator.domain.application.exceptions;

public class ApplicationAlreadyRegisteredException extends Exception {
    public ApplicationAlreadyRegisteredException(String message) {
        super(message);
    }
}
