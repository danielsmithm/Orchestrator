package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.token.exceptions.TokenNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TokenRepository {

    public Token save(Token generatedToken) {
        //TODO: Implement this.
        return null;
    }

    public Token findTokenById(UUID tokenUUID) throws TokenNotFoundException {

        //TODO: Implement persistency.
        Token token = null;

        if(token == null){
            throw new TokenNotFoundException("The token for the provided UUID wasn't found.");
        }

        return null;
    }

}
