package br.com.texo.gra;

final class Movie {

	private final String movie;
	private final int awardYear;
	
	private final Movie priorMovie;

	Movie(final String movie, final int awardYear, final Movie priorMovie) {
		this.movie = movie;
		this.awardYear = awardYear;
		this.priorMovie = priorMovie;

	}

	String getMovie() {
		return this.movie;
	}

	boolean hasPriorMovie() {
		return this.priorMovie != null;
	}

	AwardsInterval getAwardsInterval() {
		if (this.priorMovie == null) {
			return new AwardsInterval(this.awardYear, this.awardYear);
		}
		final AwardsInterval actualInterval = new AwardsInterval(this.priorMovie.awardYear, this.awardYear);
		final AwardsInterval priorInterval = this.priorMovie.getAwardsInterval();
		if (priorInterval == null) {
			return actualInterval;
		}
		final int priorDiff = priorInterval.getInterval();
		final int actualDiff = actualInterval.getInterval();
		return priorDiff > actualDiff ? priorInterval: actualInterval;
	}

}
