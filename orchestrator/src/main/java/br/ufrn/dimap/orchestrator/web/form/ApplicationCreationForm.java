package br.ufrn.dimap.orchestrator.web.form;

public class ApplicationCreationForm {
	
	private String appspot;
	private String ownerName;
	private String password;

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
}
