package br.com.texo.gra;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "year", "title", "studios", "producers", "winner" })
final class MovieCSV {

	public int year;
	public String title;
	public String studios;
	public String[] producers;
	public String isWinner;

	@Override
	public String toString() {
		return "MovieCSV [year=" + this.year + ", title=" + this.title + ", studios=" + this.studios + ", producers="
				+ Arrays.toString(this.producers) + ", isWinner=" + this.isWinner + "]";
	}

}
