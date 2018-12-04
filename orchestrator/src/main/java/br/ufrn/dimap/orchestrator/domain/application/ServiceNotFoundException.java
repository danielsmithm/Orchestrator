package br.ufrn.dimap.orchestrator.domain.application;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(String message){
        super(message);
    }
}
