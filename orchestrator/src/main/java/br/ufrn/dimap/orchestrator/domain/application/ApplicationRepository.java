package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.EntityQuery.Builder;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class ApplicationRepository {

    public static final String APPSPOT_FIELD = "appspot";
    public static final String OWNER_FIELD = "owner";
    public static final String APPLICATION_ENTITY_NAME = "Application";
    private static final String PASSWORD_FIELD = "password";
    private static final String APPNAME_FIELD = "appname";
    private static final String GOOGLE_SERVICES_FIELD = "googleservices";
    private static final String APPDESCRIPTION_FIELD = "appdescription";
    private static final String FIWAREUSAGES_FIELD = "fiwarecount";

    private final Datastore datastore;
    private final KeyFactory keyFactory;

    @Autowired
    public ApplicationRepository(Datastore datastore) {
        this.datastore = Objects.requireNonNull(datastore, "The datastore object could not be null.");
        this.keyFactory = datastore.newKeyFactory().setKind(APPLICATION_ENTITY_NAME);
    }

    public List<Application> search(String search){
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(APPLICATION_ENTITY_NAME)
                .build();
        QueryResults<Entity> result = datastore.run(query);

        List<Application> apps = new ArrayList<>();
        
        while(result.hasNext()) {
        	Entity e = result.next();
        	Application app = convertToApplication(e);
        	
        	if (search != null) {
        		
        		search = search.toLowerCase();
        		
        		if (app.getAppName().toLowerCase().contains(search)
        		   || app.getAppDescription().toLowerCase().contains(search)
        		   || app.getAppspot().getAppspotName().toLowerCase().contains(search)
        		   || app.getOwnerName().toLowerCase().contains(search))
        			apps.add(app);
        	} else {
        		apps.add(app);
        	}
        }
    	
    	return apps;
    }
    
    public Application findByAppspot(Appspot serverAppspot) throws ApplicationNotFoundException {

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(APPLICATION_ENTITY_NAME)
                .setFilter(PropertyFilter.eq("__key__",
                        keyFactory.newKey(serverAppspot.getAppspotName())))
                .build();

        QueryResults<Entity> result = datastore.run(query);

        if(!result.hasNext()){
            throw new ApplicationNotFoundException("An application with the provided appspot was not found.");
        }

        Application application = convertToApplication(result.next());

        if(result.hasNext()){
            throw new IllegalStateException("The query returned more than one result, when a single result was expected");
        }

        return application;
    }

    private Application convertToApplication(Entity entity) {
        Application application = new Application();

        application.setAppspot(Appspot.from(entity.getString(APPSPOT_FIELD)));
        application.setOwnerName(entity.getString(OWNER_FIELD));
        application.setPassword(entity.getString(PASSWORD_FIELD));
        application.setAppName(entity.getString(APPNAME_FIELD));
        application.setAppDescription(entity.getString(APPDESCRIPTION_FIELD));
        
        if (entity.contains(FIWAREUSAGES_FIELD))
        	application.setFiwareUsesCount(entity.getLong(FIWAREUSAGES_FIELD));
        else
        	application.setFiwareUsesCount(0L);
        
        application.setGoogleServiceUse(
        		entity
        		.getList(GOOGLE_SERVICES_FIELD)
        		.stream()
        		.map(s -> new ServiceUse(application, GoogleCloudService.valueOf(s.get().toString())))
        		.collect(Collectors.toList())
        		);
        

        return application;
    }

    public List<Application> findAllApplications() {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(APPLICATION_ENTITY_NAME)
                .build();

        QueryResults<Entity> result = datastore.run(query);

        List<Application> apps = new CopyOnWriteArrayList<>();
        result.forEachRemaining(entity -> apps.add(convertToApplication(entity)));

        return apps;
    }

    public Application save(Application application) {
        Key key = keyFactory.reset().setKind(APPLICATION_ENTITY_NAME)
        		.newKey(application.getAppspot().getAppspotName());
        Entity app = Entity.newBuilder(key)
                .set(APPSPOT_FIELD, application.getAppspot().getAppspotName())
                .set(OWNER_FIELD, application.getOwnerName())
                .set(PASSWORD_FIELD,application.getPassword())
                .set(APPNAME_FIELD,application.getAppName())
                .set(APPDESCRIPTION_FIELD, application.getAppDescription())
                .set(FIWAREUSAGES_FIELD, application.getFiwareUsesCount())
                .set(GOOGLE_SERVICES_FIELD, 
                		application
                		.getGoogleServiceUse()
                		.stream()
                		.map(u -> StringValue.of(u.getGoogleService().name()))
                		.collect(Collectors.toList()))
                .build();
        // TODO add.html services etc
        Entity put = datastore.put(app);
        // TODO check how to retrieve app id
        return application;
    }

    public void remove(Appspot appspot) {
        Key key = keyFactory.newKey(appspot.getAppspotName());
        datastore.delete(key);
    }

    public Application removeProvidedService(int providedServiceId) {
        return null;
    }

    public boolean existsApplicationWithAppspot(Appspot appspot) {
        try{
            return findByAppspot(appspot) != null;
        } catch (ApplicationNotFoundException e) {
            return false;
        }
    }
}
