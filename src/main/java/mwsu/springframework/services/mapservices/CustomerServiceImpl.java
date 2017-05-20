package mwsu.springframework.services.mapservices;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.converters.CustomerFormToCustomer;
import mwsu.springframework.domain.Customer;
import mwsu.springframework.domain.DomainObject;
import mwsu.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Customer saveOrUpdateForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);
        if(newCustomer.getUser().getId() != null){
            Customer existingCustomer = getById(newCustomer.getId());
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
    }
}
