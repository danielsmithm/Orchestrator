package br.ufrn.dimap.orchestrator.application;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    private final TokenFactory tokenFactory;

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenFactory tokenFactory, TokenRepository tokenRepository) {
        this.tokenFactory = tokenFactory;
        this.tokenRepository = tokenRepository;
    }

    public Token validate(UUID tokenUUID, Appspot clientAppspot, Appspot serverAppspot, String serviceName) throws InvalidTokenException, TokenNotFoundException, TokenAlreadyValidatedException {

        if(tokenUUID == null){
            throw new InvalidTokenException("The token UUID is required.");
        }

        Token tokenToValidate = tokenRepository.findTokenById(tokenUUID);

        tokenToValidate.validate(clientAppspot,serverAppspot,serviceName);

        return tokenRepository.save(tokenToValidate);
    }

    public Token generateToken(Appspot clientAppspot, Appspot serverAppspot, String serviceName){
        Token generatedToken = tokenFactory.generateToken(clientAppspot,serverAppspot,serviceName);

        return tokenRepository.save(generatedToken);
    }

}
