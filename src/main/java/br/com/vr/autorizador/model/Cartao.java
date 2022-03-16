package br.com.vr.autorizador.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cartao")
public class Cartao {
	
	@Id
	private String numero;
	
	private String senha;
	
	private BigDecimal saldo; 	
	
	public Cartao() {
		
	}
	
	public Cartao(String numero, String senha, BigDecimal saldo) {
		super();
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	

}
