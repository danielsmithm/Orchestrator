package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.application.*;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.AppNameNotInformedException;
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

    public Application registerApplication(
    		Appspot appspot, 
    		String ownerName, 
    		String password,
    		String appName, 
    		String appDescription, 
    		List<GoogleCloudService> googleServices)
    				throws ApplicationAlreadyRegisteredException, PasswordNotInformedException, AppNameNotInformedException {

        Application application = applicationFactory.createApplication(appspot,ownerName,password,appName,appDescription, googleServices);

        return applicationRepository.save(application);
    }

    public Application update(Appspot appspot, String ownerName, String password, String appName, String appDescription, List<GoogleCloudService> googleServices) throws ApplicationNotFoundException{
        
    	Application app = findApplicationByAppspot(appspot);
    	
    	app.update(ownerName, password, appName, appDescription, googleServices);
    	
    	return applicationRepository.save(app);
    }
    
    public void remove(Appspot appspot){
        applicationRepository.remove(appspot);
    }

    public Application findApplicationByAppspot(Appspot appspot) throws ApplicationNotFoundException {
        return applicationRepository.findByAppspot(appspot);
    }

	public List<Application> search(String search) {
		return applicationRepository.search(search);
	}

}
