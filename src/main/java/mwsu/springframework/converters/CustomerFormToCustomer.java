package mwsu.springframework.converters;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.domain.Address;
import mwsu.springframework.domain.Customer;
import mwsu.springframework.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by bingyang.wei on 5/11/2017.
 */
@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer>{
    @Override
    public Customer convert(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setBillingAddress(new Address());
        customer.setShippingAddress(new Address());
        customer.getUser().setId(customerForm.getUserId());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.setId(customerForm.getCustomerId());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhone(customerForm.getPhoneNumber());
        return customer;
    }
}
