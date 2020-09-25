package com.devhoss.service;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.Cambio;

import com.devhoss.repository.ITipoCambioRepository;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;



@Service
public class TipoCambioServiceImpl implements ITipoCambioService {

	@Autowired
	private ITipoCambioRepository iTipoCambioRepository;

	//private static final Logger log = LoggerFactory.logger(caller)(TipoCambioServiceImpl.class);

	@Override
	public TipoCambio Save(TipoCambio tipoCambio) {

		TipoCambio savedTipoCambio= iTipoCambioRepository.save(tipoCambio);
		return savedTipoCambio;

	}

	@Override
	public Cambio change(Cambio request) {
		TipoCambio tipo = iTipoCambioRepository.findByMonedaOrigen(request.getMonedaOrigen()).get(0);

		return CalcularCambio(tipo,request);

	}

	private Cambio CalcularCambio(TipoCambio cambio, Cambio request) {
		Double respuesta = 0.0;
		Double tipoCambio = 0.0;
		if(cambio.getTipoCambioOrigen() < cambio.getTipoCambioDestino()) {
			respuesta = (request.getMonto()/cambio.getTipoCambioDestino());
			tipoCambio = cambio.getTipoCambioDestino();
		}
		else { 
			respuesta = request.getMonto()*cambio.getTipoCambioOrigen();
			tipoCambio = cambio.getTipoCambioOrigen();
		}
		request.setMonto(respuesta);
		request.setTipoCambio(tipoCambio);

		return request;
	}

	@Override
	public Observable<TipoCambio> GetAll() {
		return Observable.fromIterable(iTipoCambioRepository.findAll());
		
		
		/*
		 * return Observable.<TipoCambio>create(sub -> { Observable<TipoCambio>
		 * listaTiposCambios = Observable.fromIterable(iTipoCambioRepository.findAll());
		 * sub.onNext(listaTiposCambios); sub.onComplete(); }).doOnNext(p ->
		 * System.out.print("Tipos cambio received successfully.")) .doOnError(e ->
		 * System.out.print("Tipo cambio were received Error."));
		 */

		//Observable<TipoCambio> listaTiposCambio = Observable.fromIterable(iTipoCambioRepository.findByMonedaOrigen(request.getMonedaOrigen()))
		//		.subscribeOn(Schedulers.newThread());


	}


	public Cambio change2(Cambio request) {

		Observable<TipoCambio> listaTiposCambio = Observable.fromIterable(iTipoCambioRepository.findByMonedaOrigen(request.getMonedaOrigen()))
				.subscribeOn(Schedulers.newThread());


		//return CalcularCambio(tipo,request);
		return null;
	}




}
