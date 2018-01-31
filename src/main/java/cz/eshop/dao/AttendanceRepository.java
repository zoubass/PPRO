package cz.eshop.dao;

import cz.eshop.model.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttendanceRepository extends CrudRepository<Attendance, Long>{
}
