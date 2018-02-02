package cz.eshop.dao;

import cz.eshop.model.Reminder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReminderRepository extends CrudRepository<Reminder, Long> {
    Reminder findById(@Param("id") Long id);
}
