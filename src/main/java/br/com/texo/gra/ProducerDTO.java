package br.com.texo.gra;

public final class ProducerDTO {

	private final String producer;
	private final int interval;
	private final int previousWin;
	private final int followingWin;

	public ProducerDTO(final String producer, final int interval, final int previousWin, final int followingWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return this.producer;
	}

	public int getInterval() {
		return this.interval;
	}

	public int getPreviousWin() {
		return this.previousWin;
	}

	public int getFollowingWin() {
		return this.followingWin;
	}

}
