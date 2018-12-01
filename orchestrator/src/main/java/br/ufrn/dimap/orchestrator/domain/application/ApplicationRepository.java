package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import java.util.List;
import java.util.Objects;

@Repository
public class ApplicationRepository{

	private final Datastore datastore;
	private final KeyFactory keyFactory;

	@Autowired
	public ApplicationRepository(Datastore datastore) {
		this.datastore = Objects.requireNonNull(datastore,"The datastore object could not be null.");
		this.keyFactory = datastore.newKeyFactory().setKind("App");
	}
	
	public Application findByAppspot(Appspot serverAppspot) throws ApplicationNotFoundException {
		
		Query<Entity> query = Query.newEntityQueryBuilder()
				.setKind("App")
				.setFilter(PropertyFilter.eq("__key__", 
						keyFactory.newKey(serverAppspot.getAppspotName())))
				.build();
		QueryResults<Entity> apps = datastore.run(query);
		Application appReturn = null;
		while (apps.hasNext()) {
			Entity app = apps.next();
			appReturn = new Application();
			//TODO populate app
		}
		return appReturn;
	}

	public List<Application> findAllApplications() {
		return null;
	}

	public Application save(Application application) {	
		Key key = keyFactory.newKey(application.getAppspot().getAppspotName());
		Entity app = Entity.newBuilder(key)
		.set("appspot", application.getAppspot().getAppspotName())
		.set("owner", application.getOwnerName())
		.build();
		// TODO add services etc
		//datastore.put(app);
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
		return false;
	}
}
