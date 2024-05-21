package com.aluracursos.BooksAPI;

import com.aluracursos.BooksAPI.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();

	}
}
