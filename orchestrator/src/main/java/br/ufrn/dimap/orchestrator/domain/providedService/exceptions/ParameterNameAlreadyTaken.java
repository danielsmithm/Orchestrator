package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ParameterNameAlreadyTaken extends ValidationException {
    public ParameterNameAlreadyTaken(String message) {
        super(message);
    }
}
