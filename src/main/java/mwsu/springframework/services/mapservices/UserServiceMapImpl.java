package mwsu.springframework.services.mapservices;

import mwsu.springframework.domain.DomainObject;
import mwsu.springframework.domain.User;
import mwsu.springframework.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by bingyang.wei on 5/15/2017.
 */
public class UserServiceMapImpl extends AbstractMapService implements UserService{


    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User)super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public User findByUserName(String name) {
        Optional returnUser = domainMap.values().stream().filter(new Predicate<DomainObject>() {
            @Override
            public boolean test(DomainObject domainObject) {
                User user = (User)domainObject;
                return user.getUsername().equalsIgnoreCase(name);
            }
        }).findFirst();
        return (User)returnUser.get();
    }
}
