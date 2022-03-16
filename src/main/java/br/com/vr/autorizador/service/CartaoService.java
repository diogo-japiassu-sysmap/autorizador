package br.com.vr.autorizador.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

import br.com.vr.autorizador.dto.CartaoDto;
import br.com.vr.autorizador.dto.TransacaoDto;
import br.com.vr.autorizador.enumerators.StatusTransacao;

public interface CartaoService {

	HttpStatus criarCartao(CartaoDto cartaoDto);

	BigDecimal obterSaldoCartao(String numeroCartao);

	StatusTransacao realizarTransacao(TransacaoDto transacaoDto);

}
