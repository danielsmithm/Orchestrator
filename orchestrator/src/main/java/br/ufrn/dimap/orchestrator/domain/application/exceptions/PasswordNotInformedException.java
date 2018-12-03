package br.ufrn.dimap.orchestrator.domain.application.exceptions;

public class PasswordNotInformedException extends Throwable {
    public PasswordNotInformedException(String message) {
        super(message);
    }
}
