package br.com.vr.autorizador.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.autorizador.dto.CartaoDto;
import br.com.vr.autorizador.model.Cartao;
import br.com.vr.autorizador.repository.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	private static final BigDecimal SALDO_PADRAO_ABERTURA = BigDecimal.valueOf(500);
	
	public CartaoDto criarCartao(CartaoDto cartaoDto) {
		String senhaEcriptografada = DigestUtils.md5Hex(cartaoDto.getSenha());
		Cartao cartao = new Cartao(cartaoDto.getNumeroCartao(), senhaEcriptografada, SALDO_PADRAO_ABERTURA);
		cartaoRepository.save(cartao);
		
		return cartaoDto;
	}
	
	public BigDecimal obterSaldoCartao(String numeroCartao) throws NoSuchElementException {
		Optional<Cartao> cartao = cartaoRepository.findById(numeroCartao);
		return cartao.get().getSaldo();
	}

}
