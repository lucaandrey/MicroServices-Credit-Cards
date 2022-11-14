package com.luca.mscartoes.application.representation;

import java.math.BigDecimal;

import com.luca.mscartoes.domain.BandeiraCartao;
import com.luca.mscartoes.domain.Cartao;

import lombok.Data;

@Data
public class CartaoSaveRequest {
	
	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limiteBasico;
	
	public Cartao toModel() {
		return new Cartao(nome, bandeira, renda, limiteBasico);
	}

}
