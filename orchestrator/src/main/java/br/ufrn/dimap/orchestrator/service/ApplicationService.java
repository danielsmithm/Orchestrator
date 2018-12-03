package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.application.*;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationFactory applicationFactory;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ApplicationFactory applicationFactory) {
        this.applicationRepository = applicationRepository;
        this.applicationFactory = applicationFactory;
    }

    public List<Application> findAllApplications(){
        return applicationRepository.findAllApplications();
    }

    public Application registerApplication(Appspot appspot, String ownerName, String password) throws ApplicationAlreadyRegisteredException, PasswordNotInformedException {
        Application application = applicationFactory.createApplication(appspot,ownerName,password);

        return applicationRepository.save(application);
    }

    public Application update(Application application){
        return applicationRepository.save(application);
    }

    public void remove(Appspot appspot){
        applicationRepository.remove(appspot);
    }

    public Application findApplicationByAppspot(Appspot appspot) throws ApplicationNotFoundException {
        return applicationRepository.findByAppspot(appspot);
    }

    public Application addProvidedService(Appspot appspot, String serviceName, String serviceDescription) throws ApplicationNotFoundException {
        Application application = applicationRepository.findByAppspot(appspot);

        application.addProvidedService(serviceName, serviceDescription);

        return applicationRepository.save(application);
    }

    public void removeProvidedService(Appspot appspot, int providedServiceId) throws ApplicationNotFoundException {
        Application application = applicationRepository.findByAppspot(appspot);

        applicationRepository.removeProvidedService(providedServiceId);
    }

}
