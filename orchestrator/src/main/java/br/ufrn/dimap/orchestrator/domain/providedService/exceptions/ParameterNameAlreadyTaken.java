package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

public class ParameterNameAlreadyTaken extends Exception {
    public ParameterNameAlreadyTaken(String message) {
        super(message);
    }
}
