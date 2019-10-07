package br.com.texo.gra;

public final class AwardsIntervalDTO {

	private final ProducerDTO[] min;
	private final ProducerDTO[] max;
	
	public AwardsIntervalDTO(final ProducerDTO[] min, final ProducerDTO[] max) {
		this.min = min;
		this.max = max;
	}

	public ProducerDTO[] getMin() {
		return this.min;
	}

	public ProducerDTO[] getMax() {
		return this.max;
	}

}
