package cz.eshop.dao;

import cz.eshop.model.Feedback;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by t922274 on 11.5.2017.
 */
public interface FeedbackDao extends CrudRepository<Feedback, Long> {
}
