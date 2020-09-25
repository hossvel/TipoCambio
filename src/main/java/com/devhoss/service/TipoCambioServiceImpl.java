package com.devhoss.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.Cambio;
import com.devhoss.repository.ITipoCambioRepository;
import io.reactivex.Observable;
import io.reactivex.Single;


@Service
public class TipoCambioServiceImpl implements ITipoCambioService {

	@Autowired
	private ITipoCambioRepository iTipoCambioRepository;

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
	}

	@Override
	public Single<Cambio> CambioMoneda(Cambio request) {

		return	Single.zip(
				ObtenerTipoCambio(request.getMonedaOrigen()),
				ConvertToSingle(request),

				(o1,o2)->{
					return CalcularCambio(o1,o2);

				});

	}

	private Single<TipoCambio> ObtenerTipoCambio(String MonedaOrigen) {

		Single<TipoCambio> tipoSingle = Single.just(iTipoCambioRepository.findByMonedaOrigen(MonedaOrigen).get(0));
		return tipoSingle;

	}


	private Single<Cambio> ConvertToSingle(Cambio request) {

		Single<Cambio> tipoSingle = Single.just(request);
		return tipoSingle;

	}

}
