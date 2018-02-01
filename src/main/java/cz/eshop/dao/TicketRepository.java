package cz.eshop.dao;

import cz.eshop.model.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TicketRepository extends CrudRepository<Parent, Long> {
}
