package br.com.alura.ScreenMatch2;

import br.com.alura.ScreenMatch2.model.SeriesData;
import br.com.alura.ScreenMatch2.service.APIConsumn;
import br.com.alura.ScreenMatch2.service.DataConversor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatch2Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var apiConsumn = new APIConsumn();
		var json = apiConsumn.obterDados("https://omdbapi.com/?t=Breaking+Bad&apikey=9bce434c");
		System.out.println(json);
		DataConversor conversor = new DataConversor();
		SeriesData data = conversor.getData(json, SeriesData.class);
		System.out.println(data);
	}
}

