package cz.eshop.dao;

import cz.eshop.model.Parent;
import cz.eshop.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Ticket findById(@Param("id") Long id);
}
