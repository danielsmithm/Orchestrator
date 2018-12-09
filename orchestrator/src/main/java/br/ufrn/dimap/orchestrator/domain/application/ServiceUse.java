package br.ufrn.dimap.orchestrator.domain.application;

import java.io.Serializable;

public class ServiceUse implements Serializable {

	private Application application;
	private GoogleCloudService googleService;
	
	public ServiceUse(Application application, GoogleCloudService googleService) {
		this.setApplication(application);
		this.setGoogleService(googleService);
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public GoogleCloudService getGoogleService() {
		return googleService;
	}

	public void setGoogleService(GoogleCloudService googleService) {
		this.googleService = googleService;
	}
	
}
