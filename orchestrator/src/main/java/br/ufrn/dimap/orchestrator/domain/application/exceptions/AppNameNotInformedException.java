package br.ufrn.dimap.orchestrator.domain.application.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class AppNameNotInformedException extends ValidationException {
    public AppNameNotInformedException(String message) {
        super(message);
    }
}
