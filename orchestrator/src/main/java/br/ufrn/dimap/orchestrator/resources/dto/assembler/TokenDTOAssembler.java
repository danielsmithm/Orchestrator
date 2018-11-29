package br.ufrn.dimap.orchestrator.resources.dto.assembler;

import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.resources.dto.TokenDTO;

public class TokenDTOAssembler{

    public static TokenDTO fromToken(Token token){

        if(token == null){
            throw new IllegalArgumentException("Argument token must not be null.");
        }

        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setTokenUUID(token.getId().toString());
        tokenDTO.setGenerationDate(token.getGenerationDate());
        tokenDTO.setClientAppspot(token.getClientAppspot().getAppspotName());
        tokenDTO.setServerAppspot(token.getServerAppspot().getAppspotName());
        tokenDTO.setServiceName(token.getServiceName());

        return tokenDTO;
    }

}
