package br.ufrn.dimap.orchestrator.web.form;

import br.ufrn.dimap.orchestrator.domain.application.Application;

public class ApplicationCreationForm {
	
	private String appspot;
	private String ownerName;
	private String password;
	private String passwordConfirmation;

	public String getAppspot() {
		return appspot;
	}

	public void setAppspot(String appspot) {
		this.appspot = appspot;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public static ApplicationCreationForm from(Application app) {
		ApplicationCreationForm appForm = new ApplicationCreationForm();
		appForm.setAppspot(app.getAppspot().getAppspotName());
		appForm.setOwnerName(app.getOwnerName());		
		return null;
	}
}
