package br.ufrn.dimap.orchestrator.domain.application;

public class ParameterNameAlreadyTaken extends Exception {
    public ParameterNameAlreadyTaken(String message) {
        super(message);
    }
}
