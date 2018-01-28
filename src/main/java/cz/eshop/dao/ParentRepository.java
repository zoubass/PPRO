package cz.eshop.dao;

import cz.eshop.model.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ParentRepository extends CrudRepository<Parent, Long> {
}
