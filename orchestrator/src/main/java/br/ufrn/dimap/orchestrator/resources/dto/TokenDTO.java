package br.ufrn.dimap.orchestrator.resources.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the Token resources responses. 
 * 
 * @author Daniel Smith
 * @author Vitor Greati
 *
 */
public class TokenDTO {

	/**
	 * Attribute for the token-id field.
	 */
	@JsonProperty("token-id")
    private String tokenUUID;
	
	/**
	 * Attribute for the generation-date field.
	 */
	@JsonProperty("generation-date")
    private Date generationDate;
	
	/**
	 * Attribute for the client-appspot field.
	 */
	@JsonProperty("client-appspot")
    private String clientAppspot;
	
	/**
	 * Attibute for the server-appspot field.
	 */
	@JsonProperty("server-appspot")
    private String serverAppspot;
	
	/**
	 * Attribute for the service-name field.
	 */
	@JsonProperty("service-name")
    private String serviceName;

    @JsonProperty("validation-date")
	private Date validationDate;

	//Getters and setters below this line
    public String getTokenUUID() {
        return tokenUUID;
    }

    public void setTokenUUID(String tokenUUID) {
        this.tokenUUID = tokenUUID;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public String getClientAppspot() {
        return clientAppspot;
    }

    public void setClientAppspot(String clientAppspot) {
        this.clientAppspot = clientAppspot;
    }

    public String getServerAppspot() {
        return serverAppspot;
    }

    public void setServerAppspot(String serverAppspot) {
        this.serverAppspot = serverAppspot;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }
}
