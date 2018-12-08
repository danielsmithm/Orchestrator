package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

import br.ufrn.dimap.orchestrator.shared.exception.ValidationException;

public class ProvidedServiceNotFoundException extends ValidationException {
    public ProvidedServiceNotFoundException(String message){
        super(message);
    }
}
