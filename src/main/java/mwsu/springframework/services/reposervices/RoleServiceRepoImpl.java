package mwsu.springframework.services.reposervices;


import mwsu.springframework.domain.security.Role;
import mwsu.springframework.repositories.RoleRepository;

import mwsu.springframework.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
@Service
@Profile("springdatajpa")
public class RoleServiceRepoImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }


    @Override
    public void delete(Integer id) {

        Role role = roleRepository.findOne(id);
        roleRepository.delete(role);
    }
}
