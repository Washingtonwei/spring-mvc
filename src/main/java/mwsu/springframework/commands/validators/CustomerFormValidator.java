package mwsu.springframework.commands.validators;

import mwsu.springframework.commands.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by bingyang.wei on 5/12/2017.
 */
@Component
public class CustomerFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerForm customerForm = (CustomerForm) target;

        if(!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())){
            errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", "Passwords Don't Match");
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerForm.passwordTextConf", "Passwords Don't Match");
        }
    }
}
