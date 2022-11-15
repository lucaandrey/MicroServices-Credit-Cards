package com.luca.mscartoes.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luca.mscartoes.domain.ClienteCartao;
import com.luca.mscartoes.infra.repository.ClienteCartaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
	
	private final ClienteCartaoRepository repository;
	
	public List<ClienteCartao> listCartoesByCpf(String cpf){
		return repository.findByCpf(cpf);
	}

}
