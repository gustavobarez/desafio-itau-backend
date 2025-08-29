package br.com.gustavobarez.desafio_itau_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { SystemMetricsAutoConfiguration.class })
public class DesafioItauBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioItauBackendApplication.class, args);
	}

}
