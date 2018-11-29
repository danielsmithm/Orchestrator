package br.ufrn.dimap.orchestrator.resources.dto;

import java.util.Date;

public class TokenDTO {
    private String tokenUUID;
    private Date generationDate;
    private String clientAppspot;
    private String serverAppspot;
    private String serviceName;

    public void setTokenUUID(String tokenUUID) {
        this.tokenUUID = tokenUUID;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public void setClientAppspot(String clientAppspot) {
        this.clientAppspot = clientAppspot;
    }

    public void setServerAppspot(String serverAppspot) {
        this.serverAppspot = serverAppspot;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
