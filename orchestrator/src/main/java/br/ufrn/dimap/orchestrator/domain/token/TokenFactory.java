package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenFactory {

    @Autowired
    private TokenRepository tokenRepository;

    public Token generateToken(Appspot clientAppspot, Appspot serverAppspot, String serverName) {

        //TODO: Guarantee that the passed client and server information's are valid.

        Token token = new Token(clientAppspot,serverAppspot,serverName);

        return token;
    }
}
