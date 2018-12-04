package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ProvidedServiceRepository {

    public boolean hasProvidedService(Appspot appspot, String serviceName) {
        //TODO: Implement
        return false;
    }


    public ProvidedService save(ProvidedService providedService) {
        //TODO: Implement
        return null;
    }

    public ProvidedService findProvidedServiceById(int serviceId) throws ProvidedServiceNotFoundException {
        //TODO: Implement
        return null;
    }

    public void remove(int serviceId) {
        //TODO: Implement
    }
}
