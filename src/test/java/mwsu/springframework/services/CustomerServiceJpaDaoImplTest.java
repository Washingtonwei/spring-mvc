package mwsu.springframework.services;

import mwsu.springframework.domain.Customer;
import mwsu.springframework.domain.User;
import mwsu.springframework.config.JpaIntegrationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
public class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testSaveWithUser(){
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("weibingyang");
        user.setPassword("mypassword");
        customer.setUser(user);
        Customer savedCustomer = customerService.saveOrUpdate(customer);
        assert savedCustomer.getUser().getId() != null;
    }


}
