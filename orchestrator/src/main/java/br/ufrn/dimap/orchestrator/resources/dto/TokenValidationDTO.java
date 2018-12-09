package br.ufrn.dimap.orchestrator.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the Token validation request body.
 * 
 * @author Daniel Smith
 * @author Vitor Greati
 *
 */
public class TokenValidationDTO {
	
	/**
	 * Attribute for the token-id field.
	 */
    @JsonProperty("token-id")
    private String tokenId;
    
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
	 * Attribute for the service-name field.
	 */
    @JsonProperty("service-name")
    private String serviceName;

    //Getters and setters below this line
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
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
}
