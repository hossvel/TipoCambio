package com.devhoss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.Cambio;
import com.devhoss.service.ITipoCambioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/tipocambio")
@Api(value="TipoCambio", description="Operaciones con Tipos de Cambio")
public class TipoCambioController {

	@Autowired
	private ITipoCambioService iTipoCambioService;
	
	@PostMapping
	@ApiOperation(value = "cambiar tipo")
	public ResponseEntity<Cambio> change(@RequestBody Cambio request) {
		Cambio cambio = iTipoCambioService.change(request);
		return new ResponseEntity<>(cambio,HttpStatus.OK);
	}
	
	@ApiOperation(value = "obtiene la lista de Tipos de cambio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "se obtuvo la lista correctamente"),
			@ApiResponse(code = 401, message = "acceso no Autorizado"),
			@ApiResponse(code = 404, message = "no se encontro el recurso buscado")
	})
	@GetMapping
	public List<TipoCambio> findall() {
		return iTipoCambioService.FindAll();
	}
	
	/*
	@PutMapping
	public ResponseEntity<TipoCambio> create(@RequestBody TipoCambio tipoCambio) {
		TipoCambio savedPersona = iTipoCambioService.Save(tipoCambio);
		return new ResponseEntity<>(savedPersona,HttpStatus.CREATED);
	}
	*/
}
