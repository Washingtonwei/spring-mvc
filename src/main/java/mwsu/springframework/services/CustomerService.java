package mwsu.springframework.services;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.domain.Customer;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
public interface CustomerService extends CRUDService<Customer>{
    Customer saveOrUpdateForm (CustomerForm customerForm);
}
