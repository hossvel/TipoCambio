package com.devhoss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhoss.model.TipoCambio;
import com.devhoss.service.ITipoCambioService;

@RestController
@RequestMapping(value = "/cambio")
public class TipoCambioController {

	@Autowired
	private ITipoCambioService iTipoCambioService;
	
	@PostMapping
	public ResponseEntity<TipoCambio> create(@RequestBody TipoCambio tipoCambio) {
		TipoCambio savedPersona = iTipoCambioService.Save(tipoCambio);
		return new ResponseEntity<>(savedPersona,HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<TipoCambio> findall() {
		return iTipoCambioService.FindAll();
	}
	
}
