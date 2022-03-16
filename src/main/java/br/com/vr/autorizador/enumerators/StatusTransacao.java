package br.com.vr.autorizador.enumerators;

import org.springframework.http.HttpStatus;

public enum StatusTransacao {

	OK(HttpStatus.CREATED), 
	SALDO_INSUFICIENTE(HttpStatus.UNPROCESSABLE_ENTITY), 
	SENHA_INVALIDA(HttpStatus.UNPROCESSABLE_ENTITY), 
	CARTAO_INEXISTENTE(HttpStatus.UNPROCESSABLE_ENTITY);
	
	private HttpStatus httpStatus;
	
	StatusTransacao(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}	
	
}
