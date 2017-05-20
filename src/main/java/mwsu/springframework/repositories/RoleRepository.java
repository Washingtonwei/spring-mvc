package mwsu.springframework.repositories;


import mwsu.springframework.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer>{
}
