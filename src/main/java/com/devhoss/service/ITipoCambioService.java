package com.devhoss.service;

import java.util.List;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.Cambio;

public interface ITipoCambioService {
	
	public TipoCambio Save(TipoCambio tipoCambio);

	public List<TipoCambio> FindAll();
	
	public Cambio change(Cambio request);
	
}
