package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProvidedServiceRepository {

    public boolean hasProvidedService(Appspot appspot, String serviceName) {
        //TODO: Implement
        return false;
    }

    public List<ProvidedService> listByApplication(Appspot appspot){
    	//TODO: implement
    	return null;
    }

    public ProvidedService save(ProvidedService providedService) {
        //TODO: Implement
        return null;
    }

    public ProvidedService findProvidedServiceById(String serviceId) throws ProvidedServiceNotFoundException {
        //TODO: Implement
        return null;
    }

    public void remove(String serviceId) {
        //TODO: Implement
    }
    
    public ServiceParameter addParameter(ServiceParameter serviceParameter) {
    	//TODO: Implement
    	return null;	
    }

	public void removeParameter(String parameterId) {
		// TODO implement
		
	}
}
