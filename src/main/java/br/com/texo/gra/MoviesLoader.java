package br.com.texo.gra;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

final class MoviesLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MoviesLoader.class);

	private final MovieRepository movieRepository;

	private final ProducerRepository producerRepository;

	MoviesLoader(final MovieRepository movieRepository, final ProducerRepository producerRepository) {
		this.movieRepository = movieRepository;
		this.producerRepository = producerRepository;
	}

	private static List<MovieCSV> readFile(final File csvFile) throws Exception {
		final CsvMapper mapper = new CsvMapper();
//		TODO final MappingIterator<MovieCSV> iterator = mapper.readerWithTypedSchemaFor(MovieCSV.class).readValues(csvFile);
		final CsvSchema sclema = mapper.schemaFor(MovieCSV.class) //
				.withSkipFirstDataRow(true) //
				.withColumnSeparator(';') //
				.withArrayElementSeparator(", ") //
				.withoutQuoteChar();

		final MappingIterator<MovieCSV> iterator = mapper.readerFor(MovieCSV.class) //
				.with(sclema) //
				.readValues(csvFile);

		return iterator.readAll();
	}

	private static ProducerEntity createProducerEntity(final String producer) {
		final String[] names = producer.split(" ");
		String firstName = "";
		String middleName = "";
		String lastName = "";
		for (int index = 0; index < names.length; index++) {
			if (firstName.trim().equals("")) {
				firstName = names[index];
				continue;
			}
			if (middleName.trim().equals("")) {
				middleName = names[index];
				continue;
			}
			if (lastName.trim().equals("")) {
				lastName = names[index];
				continue;
			}
		}
		if (lastName.trim().equals("")) {
			lastName = middleName;
			middleName = "";
		}
		return new ProducerEntity(firstName.trim(), middleName.trim(), lastName.trim());
	}

	private void csvToEntity(final MovieCSV movie) {
		final boolean isWinner = "yes".equals(movie.isWinner);
		final MovieEntity movieEntity = new MovieEntity(movie.year, movie.title, movie.studios, isWinner);
		this.movieRepository.save(movieEntity);
		log.info("Loaded: " + movieEntity.toString());

		Arrays.stream(movie.producers).forEach(producers -> {
			for (final String producer : producers.split(" and ")) {
				ProducerEntity producerEntity = this.producerRepository.getByFullName(producer);
				if (producerEntity == null) {
					producerEntity = MoviesLoader.createProducerEntity(producer);
					this.producerRepository.save(producerEntity);
					log.info("Loaded: " + producerEntity.toString());
				}
				producerEntity.addMovie(movieEntity);
				this.producerRepository.save(producerEntity);
			}
		});
	}

	@Override
	public final void run(final String... args) throws Exception {
		log.info("Load movies and producers from CSV:");
		final File csvFile = new File("src/main/resources/movielist.csv");
		final List<MovieCSV> movies = MoviesLoader.readFile(csvFile);
		movies.forEach(movie -> this.csvToEntity(movie));
	}

}
