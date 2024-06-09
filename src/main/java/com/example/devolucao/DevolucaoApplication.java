package com.example.devolucao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.example.devolucao")
public class DevolucaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevolucaoApplication.class, args);
	}

}
