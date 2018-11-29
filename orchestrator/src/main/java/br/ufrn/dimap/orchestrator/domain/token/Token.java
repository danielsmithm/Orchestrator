package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;

import java.util.Date;
import java.util.UUID;

public class Token {

    private UUID id;
    private Appspot clientAppspot;
    private Appspot serverAppspot;
    private String serviceName;
    private Date generationDate;
    private Date validationDate;

    public Token(Appspot clientAppspot, Appspot serverAppspot, String serviceName) {
        this.clientAppspot = clientAppspot;
        this.serverAppspot = serverAppspot;
        this.serviceName = serviceName;
    }

    public void validate(Appspot clientAppspot, Appspot serverAppspot, String serviceName) throws TokenAlreadyValidatedException{

        if(this.hasValidation()){
            throw new TokenAlreadyValidatedException("This token has been already validated.");
        }

        //TODO: Implement

    }

    public boolean hasValidation(){
        return validationDate != null;
    }

    //Getters and setters below this line.
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Appspot getClientAppspot() {
        return clientAppspot;
    }

    public void setClientAppspot(Appspot clientAppspot) {
        this.clientAppspot = clientAppspot;
    }

    public Appspot getServerAppspot() {
        return serverAppspot;
    }

    public void setServerAppspot(Appspot serverAppspot) {
        this.serverAppspot = serverAppspot;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }
}
