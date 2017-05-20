package mwsu.springframework.repositories;

import mwsu.springframework.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUsername(String name);
}
