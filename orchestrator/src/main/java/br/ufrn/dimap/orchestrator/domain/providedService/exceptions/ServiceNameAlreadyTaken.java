package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ServiceNameAlreadyTaken extends ValidationException {
    public ServiceNameAlreadyTaken(String message){
        super(message);
    }
}
