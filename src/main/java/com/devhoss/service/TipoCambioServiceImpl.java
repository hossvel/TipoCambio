package com.devhoss.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.TipoCambioRequest;
import com.devhoss.model.TipoCambioResponse;
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
	public Observable<TipoCambio> GetAll() {
		return Observable.fromIterable(iTipoCambioRepository.findAll());
	}


	@Override
	public Single<TipoCambioResponse> CambioMoneda(TipoCambioRequest request) {

		return	Single.zip(
				ObtenerTipoCambio(request.getMonedaOrigen()),
				ConvertToSingle(request),

				(o,r)->{

					return CalcularCambio(new Cambio(r.getMonto(),o.getMonedaOrigen(),o.getMonedaDestino(),o.getTipoCambioOrigen(),o.getTipoCambioDestino()));

				});

	}

	private TipoCambioResponse CalcularCambio(Cambio cambio) {
		Double respuesta = 0.0;
		Double tipoCambio = 0.0;
		if(cambio.getTipoCambioOrigen() < cambio.getTipoCambioDestino()) {
			respuesta = (cambio.getMonto()/cambio.getTipoCambioDestino());
			tipoCambio = cambio.getTipoCambioDestino();
		}
		else { 
			respuesta = cambio.getMonto()*cambio.getTipoCambioOrigen();
			tipoCambio = cambio.getTipoCambioOrigen();
		}

		return new TipoCambioResponse(respuesta,cambio.getMonedaOrigen(),cambio.getMonedaDestino(),tipoCambio);
	}


	private Single<TipoCambio> ObtenerTipoCambio(String MonedaOrigen) {

		Single<TipoCambio> tipoSingle = Single.just(iTipoCambioRepository.findByMonedaOrigen(MonedaOrigen)
				.stream()
				.findFirst()
				.orElse(null));
		return tipoSingle;

	}

	private Single<TipoCambioRequest> ConvertToSingle(TipoCambioRequest request) {

		Single<TipoCambioRequest> tipoSingle = Single.just(request);
		return tipoSingle;

	}

}
