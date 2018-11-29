package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenFactory {

    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private ApplicationRepository applicationRepository;

    public Token generateToken(Appspot clientAppspot, Appspot serverAppspot, String serviceName) throws InvalidServiceException, ApplicationNotFoundException {

        //TODO: Guarantee that the passed client and server information's are valid.

        Token token = new Token(clientAppspot,serverAppspot,serviceName);
        
        Application serverApplication = applicationRepository.findByAppspot(serverAppspot);
        
        if(!serverApplication.hasProvidedService(serviceName)) {
        	throw new InvalidServiceException("The server application doesn't provide a service with this name.");
        }
        
        //TODO: persistir no gae
        
        

        return token;
    }
}
