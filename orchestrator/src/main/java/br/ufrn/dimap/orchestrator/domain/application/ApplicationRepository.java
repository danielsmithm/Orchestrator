package br.ufrn.dimap.orchestrator.domain.application;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationRepository{

	public Application findByAppspot(Appspot serverAppspot) throws ApplicationNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Application> findAllApplications() {
		return null;
	}

	public Application save(Application application) {
		return null;
	}

	public void remove(Appspot appspot) {

	}

	public Application removeProvidedService(int providedServiceId) {
		return null;
	}

	public boolean existsApplicationWithAppspot(Appspot appspot) {
		return false;
	}
}
