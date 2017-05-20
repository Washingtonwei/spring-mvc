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
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm>{
    @Override
    public CustomerForm convert(Customer customer) {
        CustomerForm customerForm = new CustomerForm();
        customerForm.setCustomerId(customer.getId());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserVersion(customer.getUser().getVersion());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setPasswordText(customer.getUser().getPassword());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setPhoneNumber(customer.getPhone());
        return customerForm;
    }
}
