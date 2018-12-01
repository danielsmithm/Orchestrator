package br.ufrn.dimap.orchestrator.resources.dto.assembler;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.resources.dto.TokenDTO;

import java.util.UUID;

/**
 * Class that assemble TokenDTO based on the token domain object.
 * 
 * @author Daniel Smith
 *
 */
public class TokenDTOAssembler{

	/**
	 * Assemble a TokenDTO with the token domain object provided. 
	 * 
	 * @param token the token domain object
	 * @return the TokentDTO representation of the domain object
	 * @throws IllegalArgumentException if the provided token is null.
	 */
    public static TokenDTO fromToken(Token token){

        if(token == null){
            throw new IllegalArgumentException("Argument token must not be null.");
        }

        TokenDTO tokenDTO = new TokenDTO();

        UUID id = token.getId();
        if (id != null) {
            tokenDTO.setTokenUUID(id.toString());
        }

        tokenDTO.setServiceName(token.getServiceName());
        tokenDTO.setGenerationDate(token.getGenerationDate());

        Appspot clientAppspot = token.getClientAppspot();
        if(clientAppspot != null){
            tokenDTO.setClientAppspot(clientAppspot.getAppspotName());
        }

        Appspot serverAppspot = token.getServerAppspot();
        if(serverAppspot != null){
            tokenDTO.setServerAppspot(serverAppspot.getAppspotName());
        }

        return tokenDTO;
    }

}
