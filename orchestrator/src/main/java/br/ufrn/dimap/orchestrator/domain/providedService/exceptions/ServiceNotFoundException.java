package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(String message){
        super(message);
    }
}
