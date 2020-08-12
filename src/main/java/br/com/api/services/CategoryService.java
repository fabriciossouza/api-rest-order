package br.com.api.services;

import br.com.api.entity.Category;
import br.com.api.infrastructure.exceptions.BusinessException;
import br.com.api.infrastructure.exceptions.EntityNotFoundException;
import br.com.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public Category findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category Not Found!"));
    }

    public Category save(Category category){
        Category CategoryFound = repository.findByName(category.getName());
        if(CategoryFound != null){
            throw new BusinessException("Category " + category.getName() + " already existing!");
        }
        return repository.save(category);
    }

    public Category update(Category category){
        if(repository.existsById(category.getId())) {
            return repository.save(category);
        }
        throw new EntityNotFoundException("Category Not Found!");
    }

    public Category delete(Long id){
        Category category = findById(id);
        repository.delete(category);
        return category;
    }

    public List<Category> findAll(){
        return repository.findAll();
    }
}
