package br.com.texo.gra;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface ProducerRepository extends CrudRepository<ProducerEntity, Long> {

	ProducerEntity findById(final long id);

	@Query("SELECT p FROM Producer p WHERE " //
			+ "LOWER(CONCAT(p.firstName, ' ', p.lastName)) = LOWER(:fullName) OR " //
			+ "LOWER(CONCAT(p.firstName, ' ', p.middleName, ' ', p.lastName)) = LOWER(:fullName)")
	ProducerEntity getByFullName(@Param("fullName") final String fullName);

}
