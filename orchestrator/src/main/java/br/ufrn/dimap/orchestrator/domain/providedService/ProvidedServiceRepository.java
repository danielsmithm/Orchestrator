package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;

@Repository
public class ProvidedServiceRepository {

	public static final String PROV_SERVICE_ENTITY_NAME = "ProvidedService";
	
	public static final String PROVSERV_NAME_FIELD = "name";
	public static final String PROVSERV_DESC_FIELD = "description";
	public static final String PROVSERV_PATH_FIELD = "path";
	public static final String PROVSERV_VERB_FIELD = "verb";

	
    private final Datastore datastore;
    private final KeyFactory keyFactory;

    @Autowired
    public ProvidedServiceRepository(Datastore datastore) {
        this.datastore = Objects.requireNonNull(datastore, "The datastore object could not be null.");
        this.keyFactory = datastore.newKeyFactory().setKind(PROV_SERVICE_ENTITY_NAME);
    }
	
	
    public boolean hasProvidedService(Appspot appspot, String serviceName) {
        //TODO: Implement
        return false;
    }

    public List<ProvidedService> listByApplication(Appspot appspot){
    	//TODO: implement
    	return null;
    }

    public ProvidedService save(ProvidedService providedService) {
    	KeyFactory keyFactory = datastore.newKeyFactory()
    		    .addAncestors(PathElement.of(ApplicationRepository.APPLICATION_ENTITY_NAME, providedService.getAppspot().getAppspotName()))
    		    .setKind(PROV_SERVICE_ENTITY_NAME);

    	Key key = null;
    	if(providedService.getId() == null)
			key = datastore.allocateId(keyFactory.newKey());
    	else
    		key = keyFactory.newKey(providedService.getId());
    	
        Entity provService = Entity.newBuilder(key)
        		.set(PROVSERV_NAME_FIELD,providedService.getServiceName())
        		.set(PROVSERV_DESC_FIELD, providedService.getServiceDescription())
        		.set(PROVSERV_PATH_FIELD, providedService.getAccessPath())
        		.set(PROVSERV_VERB_FIELD, providedService.getHttpVerb().name())
        		.build();
        
        Entity put = datastore.put(provService);
        
        providedService.setId(put.getKey().getId());
        
        return providedService;
    }

    public ProvidedService findProvidedServiceById(Appspot appspot, Long serviceId) throws ProvidedServiceNotFoundException {
        
    	Key key = keyFactory.reset().setKind(PROV_SERVICE_ENTITY_NAME)
    			.addAncestor(PathElement.of(ApplicationRepository.APPLICATION_ENTITY_NAME, appspot.getAppspotName()))
    			.newKey(serviceId);
    	Entity e = datastore.get(key);
    	
        return makeFromEntity(appspot, e);
    }
    
    public ProvidedService makeFromEntity(Appspot appspot, Entity e) {
    	ProvidedService ps = new ProvidedService();
    	ps.setId(e.getKey().getId());
    	ps.setAppspot(appspot);
    	ps.setServiceName(e.getString("name"));
    	ps.setServiceDescription(e.getString("description"));
    	ps.setHttpVerb(HTTPVerb.valueOf(e.getString("verb")));
    	ps.setAccessPath(e.getString("path"));
    	return ps;
    }

    public void remove(Long serviceId) {
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
