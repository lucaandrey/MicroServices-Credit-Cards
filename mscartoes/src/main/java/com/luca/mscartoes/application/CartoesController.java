package com.luca.mscartoes.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.mscartoes.application.representation.CartaoSaveRequest;
import com.luca.mscartoes.domain.Cartao;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesController {
	
	private final CartaoService service;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) {
		
		Cartao cartao = request.toModel();
		service.save(cartao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		
	}
}
