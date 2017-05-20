package mwsu.springframework.services.jpaservices;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.converters.CustomerFormToCustomer;
import mwsu.springframework.domain.Customer;
import mwsu.springframework.services.CustomerService;
import mwsu.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by bingyang.wei on 5/8/2017.
 */
@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl implements CustomerService {

    private EntityManagerFactory emf;
    private EncryptionService encryptionService;
    private CustomerFormToCustomer customerFormToCustomer;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(customer.getUser() != null && customer.getUser().getPassword()!=null){
            customer.getUser().setEncryptedPassword(encryptionService.encryptString(customer.getUser().getPassword()));
        }
        Customer savedCustomer = em.merge(customer);
        em.getTransaction().commit();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
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
