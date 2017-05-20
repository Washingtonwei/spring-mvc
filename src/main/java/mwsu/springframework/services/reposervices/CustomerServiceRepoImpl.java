package mwsu.springframework.services.reposervices;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.converters.CustomerFormToCustomer;
import mwsu.springframework.domain.Customer;
import mwsu.springframework.repositories.CustomerRepository;
import mwsu.springframework.repositories.UserRepository;
import mwsu.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Customer customer = customerRepository.findOne(id);
        userRepository.delete(customer.getUser());
        customerRepository.delete(customer);
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
