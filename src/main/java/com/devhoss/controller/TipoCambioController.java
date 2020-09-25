package com.devhoss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devhoss.model.TipoCambioRequest;
import com.devhoss.service.ITipoCambioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/api/tipocambio")
@Api(value="TipoCambio", description="Operaciones con Tipos de Cambio")
public class TipoCambioController {

	@Autowired
	private ITipoCambioService iTipoCambioService;

	@PostMapping
	@ApiOperation(value = "cambiar tipo")
	public Single<ResponseEntity<Single<?>>> Change(@RequestBody TipoCambioRequest request) {

		return Single.just(ResponseEntity.ok() .contentType(MediaType.APPLICATION_JSON) 
				.body(
						iTipoCambioService.CambioMoneda(request)
						.subscribeOn(Schedulers.io())
						)
				);

	}

	@ApiOperation(value = "obtiene la lista de Tipos de cambio")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "se obtuvo la lista correctamente"),
			@ApiResponse(code = 401, message = "acceso no Autorizado"),
			@ApiResponse(code = 404, message = "no se encontro el recurso buscado")
	})	
	@GetMapping	
	public Single<ResponseEntity<Observable<?>>> FindAll() {

		return Single.just(ResponseEntity.ok() 
				.contentType(MediaType.APPLICATION_JSON) 
				.body(
						iTipoCambioService.GetAll()
						.subscribeOn(Schedulers.io())
						)
				);
	}



	/*
	@PutMapping
	public ResponseEntity<TipoCambio> create(@RequestBody TipoCambio tipoCambio) {
		TipoCambio savedPersona = iTipoCambioService.Save(tipoCambio);
		return new ResponseEntity<>(savedPersona,HttpStatus.CREATED);
	}
	 */
}
