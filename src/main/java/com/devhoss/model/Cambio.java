package com.devhoss.model;

public class Cambio {

	private Double monto;
	private String monedaOrigen;
	private String monedaDestino;
	
	private Double tipoCambioOrigen;
	private Double tipoCambioDestino;

	public Cambio() {}


	public Cambio(Double monto, String monedaOrigen, String monedaDestino, Double tipoCambioOrigen,
			Double tipoCambioDestino) {
		super();
		this.monto = monto;
		this.monedaOrigen = monedaOrigen;
		this.monedaDestino = monedaDestino;
		this.tipoCambioOrigen = tipoCambioOrigen;
		this.tipoCambioDestino = tipoCambioDestino;
	}



	public Double getTipoCambioOrigen() {
		return tipoCambioOrigen;
	}



	public void setTipoCambioOrigen(Double tipoCambioOrigen) {
		this.tipoCambioOrigen = tipoCambioOrigen;
	}

	public Double getTipoCambioDestino() {
		return tipoCambioDestino;
	}

	public void setTipoCambioDestino(Double tipoCambioDestino) {
		this.tipoCambioDestino = tipoCambioDestino;
	}

	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	

}
