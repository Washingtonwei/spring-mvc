package mwsu.springframework.repositories;

import mwsu.springframework.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
public interface OrderRepository extends CrudRepository<Order, Integer>{
}
