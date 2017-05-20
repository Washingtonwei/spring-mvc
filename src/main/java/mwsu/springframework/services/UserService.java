package mwsu.springframework.services;

import mwsu.springframework.domain.User;
import mwsu.springframework.services.CRUDService;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
public interface UserService extends CRUDService<User> {
    public User findByUserName(String name);

}
