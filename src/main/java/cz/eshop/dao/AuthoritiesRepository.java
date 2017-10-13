package cz.eshop.dao;

import cz.eshop.model.Authorities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

    @Query("select A from Authorities A where A.username=:username")
    List<Authorities> filterByUsername(@Param("username") String username);
}
