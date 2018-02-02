package cz.eshop.dao;

import cz.eshop.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrainingRepository extends CrudRepository<Training, Long> {

    Training findById(@Param("id") Long training);
}
