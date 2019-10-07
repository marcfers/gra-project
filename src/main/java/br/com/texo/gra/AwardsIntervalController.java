package br.com.texo.gra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
final class AwardsIntervalController {

	private final ProducerRepository producerRepository;

	AwardsIntervalController(final ProducerRepository producerRepository) {
		this.producerRepository = producerRepository;
	}

	private ProducerDTO toDto(final Producer producer) {
		if (producer == null) {
			return null;
		}
		final AwardsInterval awardsInterval = producer.getAwardsInterval();
		return new ProducerDTO(producer.getProducer(), awardsInterval.getInterval(), awardsInterval.getPreviousWin(),
				awardsInterval.getFollowingWin());
	}

	private List<Producer> getSortedProducers() {
		final List<Producer> producers = new ArrayList<>();

		this.producerRepository.findAll().forEach(producerEntity -> {
			Movie lastMovie = null;
			for (final MovieEntity movieEntity : producerEntity.getMovies()) {
				if (movieEntity.isWinner()) {
					final Movie movie = new Movie(movieEntity.getTitle(), movieEntity.getYear(), lastMovie);
					lastMovie = movie;
				}
			}
			if (lastMovie != null && lastMovie.hasPriorMovie()) {
				producers.add(new Producer(producerEntity.getFullName(), lastMovie));
			}
		});

		// see Producer.compareTo
		Collections.sort(producers);
		return producers;
	}

	ProducerDTO[] getProducers(final boolean isMinimalInteval) {
		final List<Producer> producers = this.getSortedProducers();
		final Producer producer = producers.get(isMinimalInteval ? 0 : producers.size() - 1);

		final List<ProducerDTO> dtos = new ArrayList<>();
		producers.forEach(actualProducer -> {
			if (producer.compareTo(actualProducer) == 0) {
				dtos.add(this.toDto(producer));
			}
		});

		return dtos.toArray(new ProducerDTO[dtos.size()]);
	}

	ProducerDTO[] getMinProducers() {
		return this.getProducers(true);
	}

	ProducerDTO[] getMaxProducers() {
		return this.getProducers(false);
	}

}
