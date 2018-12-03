package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFactory{

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(Appspot appspot, String ownerName,String password) throws ApplicationAlreadyRegisteredException, PasswordNotInformedException {

        if(applicationRepository.existsApplicationWithAppspot(appspot)){
            throw new ApplicationAlreadyRegisteredException("The application was already registered.");
        }

        if(password == null || password.isEmpty()){
            throw new PasswordNotInformedException("The password is required.");

        }

        Application application = new Application();

        application.setAppspot(appspot);
        application.setOwnerName(ownerName);
        application.setPassword(password);

        return application;
    }

}
