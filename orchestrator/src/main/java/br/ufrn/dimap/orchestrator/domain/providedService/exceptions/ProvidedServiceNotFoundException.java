package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

public class ProvidedServiceNotFoundException extends Exception {
    public ProvidedServiceNotFoundException(String message){
        super(message);
    }
}
