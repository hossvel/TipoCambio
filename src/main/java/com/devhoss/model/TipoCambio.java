package com.devhoss.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class TipoCambio {

	@Id
	@GeneratedValue
	private Long id;
	private String monedaOrigen;
	private String monedaDestino;
	private Double tipoCambioOrigen;
	private Double tipoCambioDestino;
	
	
	
	public TipoCambio() {
		super();
	}
	public TipoCambio(Long id, String monedaOrigen, String monedaDestino, Double tipoCambioOrigen,
			Double tipoCambioDestino) {
		super();
		this.id = id;
		this.monedaOrigen = monedaOrigen;
		this.monedaDestino = monedaDestino;
		this.tipoCambioOrigen = tipoCambioOrigen;
		this.tipoCambioDestino = tipoCambioDestino;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}