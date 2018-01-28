package cz.eshop.dao;

import cz.eshop.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrainingRepository extends CrudRepository<Training, Long> {
}
