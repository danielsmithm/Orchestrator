package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

@Repository
public class ProvidedServiceRepository {

	public static final String PROV_SERVICE_ENTITY_NAME = "ProvidedService";
	public static final String SERVICE_PARAM_ENTITY_NAME = "ServiceParameter";

	
	public static final String PROVSERV_NAME_FIELD = "name";
	public static final String PROVSERV_DESC_FIELD = "description";
	public static final String PROVSERV_PATH_FIELD = "path";
	public static final String PROVSERV_VERB_FIELD = "verb";

	public static final String SERVICE_PARAM_NAME_FIELD = "name";
	public static final String SERVICE_PARAM_DESC_FIELD = "description";
	public static final String SERVICE_PARAM_TYPE_FIELD = "type";
	
	
    private final Datastore datastore;
    private final KeyFactory serviceKeyFactory;
    private final KeyFactory paramKeyFactory;


    @Autowired
    public ProvidedServiceRepository(Datastore datastore) {
        this.datastore = Objects.requireNonNull(datastore, "The datastore object could not be null.");
        this.serviceKeyFactory = datastore.newKeyFactory().setKind(PROV_SERVICE_ENTITY_NAME);
        this.paramKeyFactory = datastore.newKeyFactory().setKind(SERVICE_PARAM_ENTITY_NAME);
    }
	
	
    public boolean hasProvidedService(Appspot appspot, String serviceName) {
        //TODO: Implement
        return false;
    }

    public List<ProvidedService> listByApplication(Appspot appspot){
    	
    	Query<Entity> query = Query.newEntityQueryBuilder()
    		    .setKind(PROV_SERVICE_ENTITY_NAME)
    		    .setFilter(PropertyFilter.hasAncestor(
    		        datastore.newKeyFactory().setKind(ApplicationRepository.APPLICATION_ENTITY_NAME).newKey(appspot.getAppspotName())))
    		    .build();
    	
    	QueryResults<Entity> servicesResults = datastore.run(query);
    	
    	List<ProvidedService> services = new ArrayList<>();
    	
    	while(servicesResults.hasNext()) {
    		services.add(makeFromEntity(appspot, servicesResults.next()));
    	}
    	
    	return services;
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
        
    	Key key = serviceKeyFactory.reset().setKind(PROV_SERVICE_ENTITY_NAME)
    			.addAncestor(PathElement.of(ApplicationRepository.APPLICATION_ENTITY_NAME, appspot.getAppspotName()))
    			.newKey(serviceId);
    	Entity e = datastore.get(key);
    	
        return makeFromEntity(appspot, e);
    }
    
    public List<ServiceParameter> listServiceParametersByServiceId(Long serviceId) {
    	Query<Entity> query = Query.newEntityQueryBuilder()
    		    .setKind(SERVICE_PARAM_ENTITY_NAME)
    		    .setFilter(PropertyFilter.hasAncestor(
    		        datastore.newKeyFactory().setKind(PROV_SERVICE_ENTITY_NAME).newKey(serviceId)))
    		    .build();
    	
    	QueryResults<Entity> parameterResults = datastore.run(query);
    	
    	List<ServiceParameter> parameters = new ArrayList<>();
    	
    	while(parameterResults.hasNext()) {
    		parameters.add(makeParameterFromEntity(serviceId, parameterResults.next()));
    	}
    	
    	return parameters;
    }
    
    public ServiceParameter makeParameterFromEntity(Long serviceId, Entity e) {
    	ServiceParameter param = new ServiceParameter();
    	param.setParameterId(e.getKey().getId());
    	param.setParameterName(e.getString(SERVICE_PARAM_NAME_FIELD));
    	param.setDescription(e.getString(SERVICE_PARAM_DESC_FIELD));
    	param.setParameterType(ParameterType.valueOf(e.getString(SERVICE_PARAM_TYPE_FIELD)));
    	param.setServiceId(serviceId);
    	return param;
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

    public void remove(Appspot appspot, Long serviceId) {
    	KeyFactory keyFactory = datastore.newKeyFactory()
    		    .addAncestors(PathElement.of(ApplicationRepository.APPLICATION_ENTITY_NAME, appspot.getAppspotName()))
    		    .setKind(PROV_SERVICE_ENTITY_NAME);
    	datastore.delete(keyFactory.newKey(serviceId));
    }
    
    public ServiceParameter addParameter(ServiceParameter serviceParameter) {
    	KeyFactory keyFactory = paramKeyFactory.reset()
    			.addAncestor(PathElement.of(PROV_SERVICE_ENTITY_NAME, serviceParameter.getServiceId()))
    			.setKind(SERVICE_PARAM_ENTITY_NAME);
    	Key key = datastore.allocateId(keyFactory.newKey());
    	
    	Entity entity = Entity.newBuilder(key)
    			.set(SERVICE_PARAM_NAME_FIELD, serviceParameter.getParameterName())
    			.set(SERVICE_PARAM_DESC_FIELD, serviceParameter.getDescription())
    			.set(SERVICE_PARAM_TYPE_FIELD, serviceParameter.getParameterType().name())
    			.build();
    	
    	datastore.put(entity);
    	
    	serviceParameter.setParameterId(entity.getKey().getId());
    	
    	return serviceParameter;	
    }

	public void removeParameter(Long serviceId, Long parameterId) {
    	Key key = datastore.newKeyFactory()
    		    .addAncestors(PathElement.of(PROV_SERVICE_ENTITY_NAME, serviceId))
    		    .setKind(SERVICE_PARAM_ENTITY_NAME)
    		    .newKey(parameterId);
    	datastore.delete(key);
	}
}
