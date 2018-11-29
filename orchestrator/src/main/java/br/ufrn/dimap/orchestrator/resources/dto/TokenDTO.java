package br.ufrn.dimap.orchestrator.resources.dto;

import java.util.Date;

public class TokenDTO {
    private String tokenUUID;
    private Date generationDate;
    private String clientAppspot;
    private String serverAppspot;
    private String serviceName;

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
}
