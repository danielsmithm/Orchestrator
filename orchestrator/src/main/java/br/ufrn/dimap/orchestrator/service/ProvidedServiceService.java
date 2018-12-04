package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.*;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvidedServiceService {

    private final ProvidedServiceFactory factory;
    private final ProvidedServiceRepository repository;

    @Autowired
    public ProvidedServiceService(ProvidedServiceRepository repository, ProvidedServiceFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }
    
    public List<ProvidedService> listByApplication(Appspot appspot) {
    	return this.repository.listByApplication(appspot);
    }

    public ProvidedService findProvidedServiceById(String serviceId) throws ProvidedServiceNotFoundException {
    	return repository.findProvidedServiceById(serviceId);
    }
    
    public ProvidedService createProvidedService(
    		Appspot appspot, 
    		String serviceName, 
    		String serviceDescription,
    		String accessPath,
    		HTTPVerb httpVerb) 
    				throws ApplicationNotFoundException, ServiceNameAlreadyTaken {
        ProvidedService providedService = 
        		factory.createProvidedService(appspot,serviceName,serviceDescription,accessPath,httpVerb);

        return repository.save(providedService);
    }


    public ServiceParameter addParameter(String serviceId, String parameterName, ParameterType parameterType, String description) throws ServiceNotFoundException, ParameterNameAlreadyTaken, ProvidedServiceNotFoundException {

    	ServiceParameter serviceParameter = factory.createServiceParameter(serviceId, parameterName, description, parameterType);
    	
        return repository.addParameter(serviceParameter);
    }
    
    public void removeParameter(String parameterId) throws ProvidedServiceNotFoundException {
    	repository.removeParameter(parameterId);
    }

    public void removeProvidedService(String serviceId) throws ProvidedServiceNotFoundException {
        ProvidedService providedService = repository.findProvidedServiceById(serviceId);
        repository.remove(serviceId);
    }

}
