package cz.eshop.dao;

import cz.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select U from User U where U.username like CONCAT(:username, '%')")
    List<User> filterByUsername(@Param("username") String username);
}
