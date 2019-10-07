package br.com.texo.gra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraProjectApplication {

	public static void main(final String[] args) {
		SpringApplication.run(GraProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadMoviesList(final MovieRepository repository, final ProducerRepository producerRepository) {
		return new MoviesLoader(repository, producerRepository);
	}
}
