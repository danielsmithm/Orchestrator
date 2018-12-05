package br.ufrn.dimap.orchestrator.domain.application;

public class AppNameNotInformedException extends Exception {
    public AppNameNotInformedException(String message) {
        super(message);
    }
}
