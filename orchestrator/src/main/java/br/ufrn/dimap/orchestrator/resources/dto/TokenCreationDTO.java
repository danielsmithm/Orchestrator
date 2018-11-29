package br.ufrn.dimap.orchestrator.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenCreationDTO {

    @JsonProperty("client-appspot")
    private String clientAppspot;
    @JsonProperty("server-appspot")
    private String serverAppspot;
    @JsonProperty("service-name")
    private String serviceName;

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
