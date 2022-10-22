package br.com.residencia.ecommerce.exception;

import java.util.List;

public class ErrorResponse {
	private final int status;
	private final String message;
	private List<String> detalhes;

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ErrorResponse(int status, String message, List<String> detalhes) {
		super();
		this.status = status;
		this.message = message;
		this.detalhes = detalhes;
	}

	public List<String> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<String> detalhes) {
		this.detalhes = detalhes;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
