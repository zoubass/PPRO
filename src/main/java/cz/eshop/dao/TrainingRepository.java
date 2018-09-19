package cz.eshop.dao;

import cz.eshop.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface TrainingRepository extends CrudRepository<Training, Long> {

    Training findById(@Param("id") Long training);

    List<Training> findTrainingsByBeginningNotNullOrderByBeginningDesc();

    @Query("select T from Training T where T.beginning < :now and T.ending > :now")
    Training getActualTraining(@Param("now") Date now);
	
    //TODO pouze jeden výskyt
    @Query("select T from Training T where T.ending < :now order by T.ending desc")
    List<Training> getLastTraining(@Param("now") Date now);
}
