package br.com.api.services;

import br.com.api.entity.Category;
import br.com.api.entity.Product;
import br.com.api.infrastructure.exceptions.EntityNotFoundException;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;


    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not Found!"));
    }

    public Product save(Product product){
        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);
        return repository.save(product);
    }

    public Product update(Product productRequest){
        Product Product = findById(productRequest.getId());
        BeanUtils.copyProperties(productRequest, Product);
        return repository.save(productRequest);
    }

    public Product delete(Long id){
        Product product = findById(id);
        repository.delete(product);
        return product;
    }
    public List<Product> findAll(){
        return repository.findAll();
    }
}
