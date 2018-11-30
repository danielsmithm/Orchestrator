package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFactory{

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(Appspot appspot) throws ApplicationAlreadyRegisteredException {

        if(applicationRepository.existsApplicationWithAppspot(appspot)){
            throw new ApplicationAlreadyRegisteredException("The application was already registered.");
        }

        Application application = new Application();

        application.setAppspot(appspot);

        return application;
    }

}
