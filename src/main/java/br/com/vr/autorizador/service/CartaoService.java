package br.com.vr.autorizador.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vr.autorizador.dto.CartaoDto;
import br.com.vr.autorizador.dto.TransacaoDto;
import br.com.vr.autorizador.enumerators.StatusTransacao;
import br.com.vr.autorizador.model.Cartao;
import br.com.vr.autorizador.repository.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	private static final BigDecimal SALDO_PADRAO_ABERTURA = BigDecimal.valueOf(500);
	
	public HttpStatus criarCartao(CartaoDto cartaoDto) {
		return isCartaoNovo(cartaoDto.getNumeroCartao()) ? configurarNovoCartao(cartaoDto) : HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
	private boolean isCartaoNovo(String numeroCartao) {
		return !cartaoRepository.existsById(numeroCartao);
	}
	
	private String encriptarSenhaDoCartao(String senha) {
		return DigestUtils.md5Hex(senha);
	}

	private HttpStatus configurarNovoCartao(CartaoDto cartaoDto) {
		String senhaEcriptografada = encriptarSenhaDoCartao(cartaoDto.getSenha());
		Cartao cartao = new Cartao(cartaoDto.getNumeroCartao(), senhaEcriptografada, SALDO_PADRAO_ABERTURA);
		cartaoRepository.save(cartao);
		return HttpStatus.CREATED;
	}
	
	public BigDecimal obterSaldoCartao(String numeroCartao) throws NoSuchElementException {
		Optional<Cartao> cartao = cartaoRepository.findById(numeroCartao);
		return cartao.get().getSaldo();
	}
	
	@Transactional
	public StatusTransacao realizarTransacao(TransacaoDto transacaoDto) {		
		if(isCartaoNovo(transacaoDto.getNumeroCartao())) {
			return StatusTransacao.CARTAO_INEXISTENTE;
		}
		
		String senhaEcriptografada = encriptarSenhaDoCartao(transacaoDto.getSenhaCartao());
		Cartao cartao = cartaoRepository.findByNumeroAndSenha(transacaoDto.getNumeroCartao(), senhaEcriptografada);
		
		if(cartao == null || cartao.getNumero() == null) {
			return StatusTransacao.SENHA_INVALIDA;
		}
		
		if(transacaoDto.getValor().compareTo(cartao.getSaldo()) > 0) {
			return StatusTransacao.SALDO_INSUFICIENTE;
		}
		
		cartao.setSaldo(cartao.getSaldo().subtract(transacaoDto.getValor()));
		cartaoRepository.save(cartao);
		
		return StatusTransacao.OK;
	}

}
