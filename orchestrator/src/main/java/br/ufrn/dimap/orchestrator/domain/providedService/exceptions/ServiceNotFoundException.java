package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ServiceNotFoundException extends ValidationException {
    public ServiceNotFoundException(String message){
        super(message);
    }
}
