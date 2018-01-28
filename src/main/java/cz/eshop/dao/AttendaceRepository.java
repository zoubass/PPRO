package cz.eshop.dao;

import cz.eshop.model.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttendaceRepository extends CrudRepository<Attendance, Long>{
}
