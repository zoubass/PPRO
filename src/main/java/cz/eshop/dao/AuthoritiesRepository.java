package cz.eshop.dao;

import cz.eshop.model.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

	Authorities findByUsername(@Param("username") String username);
}
