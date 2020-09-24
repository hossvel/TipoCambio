package com.devhoss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.repository.ITipoCambioRepository;

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
	public List<TipoCambio> FindAll() {
		return iTipoCambioRepository.findAll();
	}

}
