package com.devhoss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.TipoCambioRequest;
import com.devhoss.model.TipoCambioResponse;
import com.devhoss.model.Cambio;
import com.devhoss.repository.ITipoCambioRepository;
import com.devhoss.utils.Constantes;

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
	public Single<?> CambioMoneda(TipoCambioRequest request) {

		if(request.getMonto() < 0 || request.getMonedaOrigen().isEmpty() || request.getMonedaDestino().isEmpty()) 
			return Single.just(Constantes.Mensaje_Datos_Ingreso);

		TipoCambio tipoCambioConfigurado = ObtenerTipoCambio(request.getMonedaOrigen(),request.getMonedaDestino());
		if(tipoCambioConfigurado.getId() == null)
			return Single.just(Constantes.Mensaje_Configuracion_No_Existe);

		try {
			return	Single.zip(
					Single.just(tipoCambioConfigurado),
					ConvertToSingle(request),

					(o,r)->{
						return CalcularCambio(new Cambio(r.getMonto(),o.getMonedaOrigen(),o.getMonedaDestino(),o.getTipoCambioOrigen(),o.getTipoCambioDestino()));
					});
		} catch (Exception ex) {
			return Single.just(ex.getMessage());
		}




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


	private TipoCambio ObtenerTipoCambio(String MonedaOrigen,String MonedaDestino) {

		return iTipoCambioRepository.findAll()
				.stream()
				.filter(x -> x.getMonedaOrigen().equals(MonedaOrigen) && x.getMonedaDestino().equals(MonedaDestino)).findFirst().orElse(new TipoCambio());

	}

	private Single<TipoCambioRequest> ConvertToSingle(TipoCambioRequest request) {

		Single<TipoCambioRequest> tipoSingle = Single.just(request);
		return tipoSingle;

	}


	@Override
	public Single<TipoCambio> Update(TipoCambio tipoCambio) {
		TipoCambio savedTipoCambio= iTipoCambioRepository.save(tipoCambio);
		return Single.just(savedTipoCambio);
	}

}
