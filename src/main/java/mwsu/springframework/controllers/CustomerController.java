package mwsu.springframework.controllers;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.converters.CustomerToCustomerForm;
import mwsu.springframework.domain.Customer;
import mwsu.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    CustomerService customerService;
    Validator customerFormValidator;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerFormValidator(Validator customerFormValidator) {
        this.customerFormValidator = customerFormValidator;
    }

    @RequestMapping({"/list", "/"})
    public String listCustomers(Model model){
        model.addAttribute("customers", customerService.listAll());
        return "customer/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    @RequestMapping("/show/{id}")
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/show";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/customerform";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateCustomer(@Valid CustomerForm customerForm,
                                       BindingResult bindingResult){

        customerFormValidator.validate(customerForm, bindingResult);
        if(bindingResult.hasErrors()){
            System.out.println("haha");
            return "customer/customerform";
        }
        Customer savedcustomer = customerService.saveOrUpdateForm(customerForm);
        return "redirect:customer/show/" + savedcustomer.getId();
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){

        Customer customer = customerService.getById(id);
        model.addAttribute("customerForm", new CustomerToCustomerForm().convert(customer));
        return "customer/customerform";
    }
}
