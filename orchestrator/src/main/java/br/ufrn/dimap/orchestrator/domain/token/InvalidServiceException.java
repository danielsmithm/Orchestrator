package br.ufrn.dimap.orchestrator.domain.token;

public class InvalidServiceException extends Exception {
	public InvalidServiceException(String message) {
		super(message);
	}
}
