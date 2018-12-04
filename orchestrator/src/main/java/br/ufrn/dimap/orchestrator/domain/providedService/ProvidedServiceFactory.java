package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNameAlreadyTaken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvidedServiceFactory {

    private final ApplicationRepository applicationRepository;
    private final ProvidedServiceRepository providedServiceRepository;

    @Autowired
    public ProvidedServiceFactory(ApplicationRepository applicationRepository, ProvidedServiceRepository providedServiceRepository) {
        this.applicationRepository = applicationRepository;
        this.providedServiceRepository = providedServiceRepository;
    }

    public ProvidedService createProvidedService(
    		Appspot appspot, 
    		String serviceName, 
    		String serviceDescription,
    		String accessPath,
    		HTTPVerb httpVerb) throws ApplicationNotFoundException, ServiceNameAlreadyTaken {

        if(applicationRepository.existsApplicationWithAppspot(appspot)){
            throw new ApplicationNotFoundException("An application with the provided appspot wasn't found.");
        }

        if(providedServiceRepository.hasProvidedService(appspot,serviceName)){
            throw new ServiceNameAlreadyTaken("The service name is already taken for the application.");
        }

        ProvidedService providedService = new ProvidedService(serviceName, serviceDescription, accessPath, httpVerb);

        return providedService;
    }
    
    public ServiceParameter createServiceParameter(
    		String serviceId,
    		String name,
    		String description,
    		ParameterType type
    		) {
    	
    	//TODO: check for nonexisting service
    	//TODO: check for already existing parameter
    	
    	ServiceParameter parameter = new ServiceParameter(name, type, description);

    	return parameter;
    }

}
