package cz.eshop.dao;

import cz.eshop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(@Param("username") String username);

	User findById(@Param("id") Long username);
	
	List<User> findUserByReminderNotNull();
}
