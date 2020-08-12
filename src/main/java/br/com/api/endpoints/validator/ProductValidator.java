package br.com.api.endpoints.validator;

import br.com.api.entity.Product;
import br.com.api.infrastructure.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProductValidator implements Validator<Product> {

    @Override
    public void validate(Product product){
        if(StringUtils.isEmpty(product.getDescription())){
            throw new ValidationException("description is mandatory");
        }

        if(StringUtils.isEmpty(product.getPrice())){
            throw new ValidationException("price is mandatory");
        }

        if(StringUtils.isEmpty(product.getCategory()) || StringUtils.isEmpty(product.getCategory().getId())){
            throw new ValidationException("category{id} is mandatory");
        }
    }
}
