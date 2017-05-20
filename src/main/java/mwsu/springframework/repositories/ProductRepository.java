package mwsu.springframework.repositories;

import mwsu.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
