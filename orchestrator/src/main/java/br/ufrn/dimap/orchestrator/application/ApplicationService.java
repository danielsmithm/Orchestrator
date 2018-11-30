package br.ufrn.dimap.orchestrator.application;

import br.ufrn.dimap.orchestrator.domain.application.*;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
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

    public Application registerApplication(Appspot appspot) throws ApplicationAlreadyRegisteredException {
        Application application = applicationFactory.createApplication(appspot);

        return applicationRepository.save(application);
    }

    public Application update(Application application){
        return applicationRepository.save(application);
    }

    public void remove(Appspot appspot){
        applicationRepository.remove(appspot);
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
