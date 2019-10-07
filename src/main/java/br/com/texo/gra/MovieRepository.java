package br.com.texo.gra;

import org.springframework.data.repository.CrudRepository;

interface MovieRepository extends CrudRepository<MovieEntity, Long> {

	MovieEntity findById(final long id);

}
