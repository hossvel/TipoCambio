package com.devhoss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devhoss.model.TipoCambio;
import com.devhoss.model.Cambio;
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

	@Override
	public Cambio change(Cambio request) {
		// TODO Auto-generated method stub
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
	


}
