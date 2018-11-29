package br.ufrn.dimap.orchestrator.resources.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenValidationDTO {
    @JsonProperty("token-id")
    private String tokenId;
    @JsonProperty("client-appspot")
    private String clientAppspot;
    @JsonProperty("server-appspot")
    private String serverAppspot;
    @JsonProperty("service-name")
    private String serviceName;

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
