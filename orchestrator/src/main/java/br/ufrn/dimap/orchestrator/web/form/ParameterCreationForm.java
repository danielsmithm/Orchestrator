package br.ufrn.dimap.orchestrator.web.form;

import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;

public class ParameterCreationForm {

	private String name;
	private String description;
	private ParameterType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ParameterType getType() {
		return type;
	}
	public void setType(ParameterType type) {
		this.type = type;
	}
	
}
