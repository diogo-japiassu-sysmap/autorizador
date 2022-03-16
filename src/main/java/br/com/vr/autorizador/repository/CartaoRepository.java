package br.com.vr.autorizador.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vr.autorizador.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String>{
	
	public Cartao findByNumeroAndSenha(String numero, String senha);

}
