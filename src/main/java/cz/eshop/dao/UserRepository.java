package cz.eshop.dao;

import cz.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(@Param("username") String username);

	User findById(@Param("id") Long username);
	
	List<User> findUserByReminderNotNull();

	List<User> findUsersByTicketNotNull();

	@Query("select U from User U where U.ticket = null and U.firstName = :fName order by U.lastName desc")
	List<User> getUsersByFirstNameWithoutTicket(@Param("fName") String fName);

	@Query("select U from User U where U.ticket = null and U.lastName = :lName order by U.firstName desc")
	List<User> getUsersByLastNameWithoutTicket(@Param("lName") String lName);

	@Query("select U from User U where U.ticket = null and U.lastName = :lName and U.firstName = :fName order by U.id desc")
	List<User> getUsersByFirstAndLastNameWithoutTicket(@Param("lName") String lName, @Param("fName") String fName);

	@Query("select U from User U where U.ticket is not null and (LOWER(U.lastName) = LOWER(:name) or LOWER(U.firstName) = LOWER(:name)) order by U.id desc")
	List<User> getUsersByNameWithTicket(@Param("name") String name);

	@Query("select U from User U where U.ticket is null and (LOWER(U.lastName) = LOWER(:name) or LOWER(U.firstName) = LOWER(:name)) order by U.id desc")
	List<User> getUsersByNameWithoutTicket(@Param("name") String name);

}
