package com.devhoss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devhoss.model.TipoCambio;

@Repository
public interface ITipoCambioRepository  extends JpaRepository<TipoCambio, String> {

}
