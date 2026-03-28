package br.com.alura.ScreenMatch2;

import br.com.alura.ScreenMatch2.main.Main;
import br.com.alura.ScreenMatch2.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ScreenMatch2Application implements CommandLineRunner {

	@Autowired
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatch2Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.displaysMenu();







	}
}

