package br.com.vr.autorizador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.CartaoDto;
import br.com.vr.autorizador.service.CartaoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = {"cartoes"}, produces = APPLICATION_JSON_VALUE)
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<CartaoDto> criarCartao(@RequestBody CartaoDto cartaoDto) {
		cartaoService.criarCartao(cartaoDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoDto);
	}
	
	@GetMapping(path = "/{numeroCartao}")
	public ResponseEntity<BigDecimal> obterSaldoCartao(@PathVariable(value = "numeroCartao") String numeroCartao) {
		try {
			BigDecimal saldo = cartaoService.obterSaldoCartao(numeroCartao);
			return ResponseEntity.ok(saldo);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<BigDecimal>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
}