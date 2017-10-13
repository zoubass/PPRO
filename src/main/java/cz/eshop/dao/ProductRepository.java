package cz.eshop.dao;

import cz.eshop.model.Produkt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepository extends CrudRepository<Produkt, Long> {

    @Query("SELECT P from Produkt P where P.orderId =:idOrder")
    List<Produkt> findByOrderId(@Param("idOrder") Long orderId);

    @Query("SELECT P from Produkt P where P.type like CONCAT(:name, '%') and P.category like CONCAT(:category, '%')")
    List<Produkt> findSpecificProducts(@Param("name") String name,/* @Param("price") BigDecimal price,*/ @Param("category") String category);
}
