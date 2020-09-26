package com.devhoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.devhoss.model.TipoCambio;
import com.devhoss.security.JWTAuthorizationFilter;
import com.devhoss.service.ITipoCambioService;
import org.springframework.http.HttpMethod;


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

			TipoCambio solesDolares = new TipoCambio(1L,"S", "$", 1.0, 3.45);
			iTipoCambioService.Save(solesDolares);

			TipoCambio dolaresSoles = new TipoCambio(2L, "$", "S", 3.40, 1.0);
			iTipoCambioService.Save(dolaresSoles);
		}
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.anyRequest().authenticated();
		}
	}

}
