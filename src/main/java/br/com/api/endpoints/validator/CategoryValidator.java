package br.com.api.endpoints.validator;

import br.com.api.entity.Category;
import br.com.api.infrastructure.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CategoryValidator implements Validator<Category> {

    @Override
    public void validate(Category category){

        if(StringUtils.isEmpty(category.getName())){
            throw new ValidationException("name is mandatory");
        }
    }
}
