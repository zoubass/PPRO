package cz.eshop.dao;

import cz.eshop.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends CrudRepository<Order, Long> {
}
