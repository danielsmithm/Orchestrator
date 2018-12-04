package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.*;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNotFoundException;
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

    public ProvidedService createProvidedService(Appspot appspot, String serviceName, String serviceDescription) throws ApplicationNotFoundException, ServiceNameAlreadyTaken {
        ProvidedService providedService = factory.createProvidedService(appspot,serviceName,serviceDescription);

        return repository.save(providedService);
    }

    public ProvidedService addParameter(int serviceId, String parameterName, ParameterType parameterType, String description) throws ServiceNotFoundException, ParameterNameAlreadyTaken, ProvidedServiceNotFoundException {

        ProvidedService providedService = repository.findProvidedServiceById(serviceId);

        providedService.addParameter(parameterName,parameterType,description);

        return repository.save(providedService);
    }

    public void removeProvidedService(int serviceId) throws ProvidedServiceNotFoundException {
        ProvidedService providedService = repository.findProvidedServiceById(serviceId);

        repository.remove(serviceId);
    }

}
