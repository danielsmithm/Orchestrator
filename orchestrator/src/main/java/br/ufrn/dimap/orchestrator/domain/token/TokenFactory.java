package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedServiceRepository;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.InvalidServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Factory that creates a token and guarantee the consistent creation of it. 
 * 
 * @author Daniel Smith
 * @author Vitor Greati
 *
 */
@Service
public class TokenFactory {

	/**
	 * Dependency of token repository.
	 */    
    private final TokenRepository tokenRepository;
    
    /**
     * Dependency of the application repository.
     */    
    private final ApplicationRepository applicationRepository;

    /**
     * Dependency of the provided service repository.
     */
    private ProvidedServiceRepository providedServiceRepository;

    @Autowired
    public TokenFactory(TokenRepository tokenRepository, ApplicationRepository applicationRepository, ProvidedServiceRepository providedServiceRepository) {
    	this.tokenRepository = tokenRepository;
    	this.applicationRepository = applicationRepository;
        this.providedServiceRepository = providedServiceRepository;
    }
    
    /**
     * 
     * @param clientAppspot
     * @param serverAppspot
     * @param serviceName
     * @return
     * @throws InvalidServiceException
     * @throws ApplicationNotFoundException
     */
    public Token generateToken(Appspot clientAppspot, Appspot serverAppspot, String serviceName) throws InvalidServiceException, ApplicationNotFoundException {
    	    	
    	if(!applicationRepository.existsApplicationWithAppspot(clientAppspot)) {
    		throw new ApplicationNotFoundException("The client application wasn't found.");
    	}
    	
    	if(!applicationRepository.existsApplicationWithAppspot(serverAppspot)) {
    		throw new ApplicationNotFoundException("The server application wasn't found.");
    	}
        
        Application serverApplication = applicationRepository.findByAppspot(serverAppspot);
        
        if(!providedServiceRepository.hasProvidedService(serverAppspot, serviceName)) {
        	throw new InvalidServiceException("The server application doesn't provide a service with this name.");
        }
        
        return new Token(clientAppspot,serverAppspot,serviceName,new Date());
    }
}
