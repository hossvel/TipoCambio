package com.devhoss.service;

import java.util.List;

import com.devhoss.model.TipoCambio;

public interface ITipoCambioService {
	
	public TipoCambio Save(TipoCambio tipoCambio);

	public List<TipoCambio> FindAll();
	
}
