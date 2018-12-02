package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.InvalidTokenException;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.TokenAlreadyValidatedException;

import java.util.Date;
import java.util.UUID;

/**
 * Class that represents an access token for an application service.
 * <p> 
 *  
 * @author danielsmith
 *
 */
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

    //Default constructor, should be used only by the persistence mechanism.
    public Token(){

    }

    public void validate(Appspot clientAppspot, Appspot serverAppspot, String serviceName) throws TokenAlreadyValidatedException, InvalidTokenException {

        if(this.hasValidation()){
            throw new TokenAlreadyValidatedException("This token has been already validated.");
        }

        if(!this.clientAppspot.equals(clientAppspot)){
            throw new InvalidTokenException("The provided client appspot isn't valid for this token.");
        }

        if(!this.serverAppspot.equals(serverAppspot)){
            throw new InvalidTokenException("The provided server appspot isn't valid for this token.");
        }

        if(!this.serviceName.equals(serviceName)){
            throw new InvalidTokenException("The provided service name isn't valid for this token.");
        }

        this.validationDate = new Date();

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
