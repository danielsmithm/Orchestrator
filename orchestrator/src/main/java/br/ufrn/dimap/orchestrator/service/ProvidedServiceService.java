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
    
    public List<ProvidedService> listByApplication(Appspot appspot, boolean loadParameters) {
    	return this.repository.listByApplication(appspot, loadParameters);
    }

    public ProvidedService findProvidedServiceById(Appspot appspot, Long serviceId) throws ProvidedServiceNotFoundException {
    	    	
    	return repository.findProvidedServiceById(appspot, serviceId);
    }
    
    public List<ServiceParameter> listServiceParameterByServiceId(Long serviceId) {
    	return repository.listServiceParametersByServiceId(serviceId);
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


    public ServiceParameter addParameter(Long serviceId, String parameterName, ParameterType parameterType, String description, ParameterScope parameterScope) throws ServiceNotFoundException, ParameterNameAlreadyTaken, ProvidedServiceNotFoundException {

    	ServiceParameter serviceParameter = factory.createServiceParameter(
    			serviceId,
				parameterName,
				description,
				parameterType,
				parameterScope
		);
    	
        return repository.addParameter(serviceParameter);
    }
    
    public void removeParameter(Long serviceId, Long parameterId) throws ProvidedServiceNotFoundException {
    	repository.removeParameter(serviceId, parameterId);
    }

    public void removeProvidedService(Appspot appspot, Long serviceId) throws ProvidedServiceNotFoundException {
        repository.remove(appspot, serviceId);
    }

	public ProvidedService update(
			Appspot appspot,
			Long serviceId, 
			String serviceName, 
			String serviceDescription, 
			String accessPath, 
			HTTPVerb httpVerb) throws ProvidedServiceNotFoundException {
		
		ProvidedService provServ = findProvidedServiceById(appspot, serviceId);
		
		provServ.update(
				serviceName, 
				serviceDescription, 
				accessPath, 
				httpVerb);
		
		repository.save(provServ);
		return provServ;
	}

}
