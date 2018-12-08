package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.AppNameNotInformedException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFactory{

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(
    		Appspot appspot, 
    		String ownerName,
    		String password, 
    		String appName, 
    		String appDescription,
    		List<GoogleCloudService> googleServices) 
    				throws ApplicationAlreadyRegisteredException, PasswordNotInformedException, AppNameNotInformedException {

        if(applicationRepository.existsApplicationWithAppspot(appspot)){
            throw new ApplicationAlreadyRegisteredException("The application was already registered.");
        }

        if(password == null || password.isEmpty()){
            throw new PasswordNotInformedException("The password is required.");

        }

        if(appName == null || appName.isEmpty()){
            throw new AppNameNotInformedException("The appname is required");
        }

        Application application = new Application();

        application.setAppspot(appspot);
        application.setOwnerName(ownerName);
        application.setPassword(password);
        application.setAppName(appName);
        application.setAppDescription(appDescription);
        application.setGoogleServiceUse(
        		googleServices.stream().map(s -> new ServiceUse(application, s)).collect(Collectors.toList()));
        return application;
    }

}
