package br.com.vr.autorizador.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.TransacaoDto;
import br.com.vr.autorizador.enumerators.StatusTransacao;
import br.com.vr.autorizador.service.CartaoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = {"transacoes"}, produces = APPLICATION_JSON_VALUE)
public class TransacaoController {

	@Autowired
	private CartaoService cartaoService;
	
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoDto transacaoDto) {
		StatusTransacao statusTransacao = cartaoService.realizarTransacao(transacaoDto);
		return ResponseEntity.status(statusTransacao.getHttpStatus()).body(statusTransacao.toString());
	}
	
}