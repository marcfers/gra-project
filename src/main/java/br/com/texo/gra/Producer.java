package br.com.texo.gra;

final class Producer implements Comparable<Producer> {

	private final String producer;
	private final Movie lastMovie;

	Producer(final String producer, final Movie lastMovie) {
		this.producer = producer;
		this.lastMovie = lastMovie;
	}

	public String getProducer() {
		return producer;
	}

	AwardsInterval getAwardsInterval() {
		return this.lastMovie.getAwardsInterval();
	}

	@Override
	public int compareTo(final Producer o) {
		return this.getAwardsInterval().compareTo(o.getAwardsInterval());
	}

}
