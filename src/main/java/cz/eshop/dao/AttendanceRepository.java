package cz.eshop.dao;

import cz.eshop.model.Attendance;
import cz.eshop.model.Training;
import cz.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttendanceRepository extends CrudRepository<Attendance, Long>{

    @Query("select A from Attendance A where A.training=:t")
    List<Attendance> filterByTraining(@Param("t")Training t);
}
