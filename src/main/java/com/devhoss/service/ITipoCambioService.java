package com.devhoss.service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.TipoCambioRequest;
import com.devhoss.model.TipoCambioResponse;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface ITipoCambioService {
	
	public TipoCambio Save(TipoCambio tipoCambio);

	public Observable<TipoCambio> GetAll();
	
	public Single<TipoCambioResponse> CambioMoneda(TipoCambioRequest request);
}
