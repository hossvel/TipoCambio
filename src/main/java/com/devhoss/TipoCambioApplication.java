package com.devhoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.devhoss.model.TipoCambio;
import com.devhoss.service.ITipoCambioService;

@SpringBootApplication
public class TipoCambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoCambioApplication.class, args);
	}

	@Component
	class DemoCommandLineRunner implements CommandLineRunner{

		@Autowired
		private ITipoCambioService iTipoCambioService;

		@Override
		public void run(String... args) throws Exception {

			TipoCambio solesDolares = new TipoCambio(3L,"S","$",1.0,3.45);
			iTipoCambioService.Save(solesDolares);
			
			TipoCambio dolaresSoles = new TipoCambio(4L,"$","S",3.42,1.0);
			iTipoCambioService.Save(dolaresSoles);
		}
	}
	
}
