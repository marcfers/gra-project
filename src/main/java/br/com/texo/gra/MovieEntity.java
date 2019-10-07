package br.com.texo.gra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Movie")
final class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int year;

	private String title;

	private String studios;

	private boolean isWinner;

	protected MovieEntity() {
	}

	public MovieEntity(final int year, final String title, final String studios, final boolean isWinner) {
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.isWinner = isWinner;
	}

	@Override
	public String toString() {
		return "MovieEntity [id=" + this.id + ", year=" + this.year + ", title=" + this.title + ", studios="
				+ this.studios + ", isWinner=" + this.isWinner + "]";
	}

	public Long getId() {
		return this.id;
	}

	public int getYear() {
		return this.year;
	}

	public String getTitle() {
		return this.title;
	}

	public String getStudios() {
		return this.studios;
	}

	public boolean isWinner() {
		return this.isWinner;
	}

}
