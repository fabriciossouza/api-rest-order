package br.com.api.endpoints.validator;

import br.com.api.entity.Customer;
import br.com.api.infrastructure.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserValidator implements Validator<Customer> {

    @Override
    public void validate(Customer customer){

        if(StringUtils.isEmpty(customer.getName())){
            throw new ValidationException("name is mandatory");
        }

        if(StringUtils.isEmpty(customer.getEmail())){
            throw new ValidationException("email is mandatory");
        }

        if(StringUtils.isEmpty(customer.getPassword())){
            throw new ValidationException("password is mandatory");
        }
    }
}
