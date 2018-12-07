package br.ufrn.dimap.orchestrator.web.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufrn.dimap.orchestrator.domain.application.Application;

public class ApplicationCreationForm {
	
	@NotNull
	@NotBlank
	private String appspot;
	
	@NotNull
	@NotBlank
	private String ownerName;
	
	@NotNull
	@NotBlank
	private String appName;
	
	private String appDescription;
	
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
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
		appForm.setAppName(app.getAppName());
		appForm.setAppDescription(app.getAppDescription());
		return appForm;
	}
}
