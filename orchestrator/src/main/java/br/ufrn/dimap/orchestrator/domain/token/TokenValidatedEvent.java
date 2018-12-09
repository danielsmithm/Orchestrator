package br.ufrn.dimap.orchestrator.domain.token;

import java.util.Date;

public class TokenValidatedEvent {

    public String tokenId;
    public Date validationDate;

    public TokenValidatedEvent(String tokenId, Date validationDate) {
        this.tokenId = tokenId;
        this.validationDate = validationDate;
    }

    public String getTokenId() {
        return tokenId;
    }

    public Date getValidationDate() {
        return validationDate;
    }
}
