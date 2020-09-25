package com.devhoss.service;

import java.util.List;


import com.devhoss.model.TipoCambio;

import io.reactivex.Observable;
import io.reactivex.Single;

import com.devhoss.model.Cambio;

public interface ITipoCambioService {
	
	public TipoCambio Save(TipoCambio tipoCambio);
	
	public Cambio change(Cambio request);

	public Observable<TipoCambio> GetAll();
	
	public Single<Cambio> CambioMoneda(Cambio request);
}
