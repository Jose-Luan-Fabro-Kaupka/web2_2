package com.trabalho.t2web2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "web2t2", version = "1", description = "Trabalho 2 de web 2"))
public class T2web2Application {

	public static void main(String[] args) {
		SpringApplication.run(T2web2Application.class, args);
	}

}
