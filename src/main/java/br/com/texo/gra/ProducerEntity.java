package br.com.texo.gra;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity(name = "Producer")
final class ProducerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	@ManyToMany(fetch = FetchType.EAGER)
	@OrderBy("year ASC")
	private Set<MovieEntity> movies;

	protected ProducerEntity() {
	}

	public ProducerEntity(final String firstName, final String middleName, final String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.movies = new HashSet<>();
	}

	public boolean addMovie(final MovieEntity movieEntity) {
		return this.movies.add(movieEntity);
	}

	@Override
	public String toString() {
		return "ProducerEntity [id=" + this.id + ", fullName=" + this.getFullName();
	}

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		final StringBuilder fullName = new StringBuilder();
		fullName.append(this.getFirstName());
		if (!(this.getMiddleName().equals(""))) {
			fullName.append(" ");
			fullName.append(this.getMiddleName());
		}
		fullName.append(" ");
		fullName.append(this.getLastName());
		return fullName.toString();
	}

	public Collection<MovieEntity> getMovies() {
		return this.movies;
	}

}
