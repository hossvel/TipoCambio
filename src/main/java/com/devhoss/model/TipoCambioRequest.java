package com.devhoss.model;

import io.swagger.annotations.ApiModelProperty;

public class TipoCambioRequest {
	
	@ApiModelProperty(notes = "Monto a Cambiar", required = true)
	private Double monto;
	 
	@ApiModelProperty(notes = "Moneda Origen es Obligatorio", required = true)
	private String monedaOrigen;
	
	@ApiModelProperty(notes = "Moneda Destino es Obligatorio", required = true)
	private String monedaDestino;
	
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
