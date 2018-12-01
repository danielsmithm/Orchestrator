package br.ufrn.dimap.orchestrator.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the body of the Token creation request.
 * 
 * @author Daniel Smith
 *
 */
public class TokenCreationDTO {

	/**
	 * Attribute for the client-appspot field.
	 */
    @JsonProperty("client-appspot")
    private String clientAppspot;
    
    /**
	 * Attribute for the server-appspot field.
	 */
    @JsonProperty("server-appspot")
    private String serverAppspot;
    
    /**
	 * Attribute for the client-appspot field.
	 */
    @JsonProperty("service-name")
    private String serviceName;

    //Getters and setters below this line.
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
}
