package br.ufrn.dimap.orchestrator.domain.providedService.exceptions;

public class ServiceNameAlreadyTaken extends Exception{
    public ServiceNameAlreadyTaken(String message){
        super(message);
    }
}
