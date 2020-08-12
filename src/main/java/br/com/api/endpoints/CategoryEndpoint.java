package br.com.api.endpoints;

import br.com.api.endpoints.validator.CategoryValidator;
import br.com.api.entity.Category;
import br.com.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rs/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryEndpoint {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryValidator validator;

    @GetMapping
    public List<Category> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        validator.validate(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(category));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        category.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.update(category));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.delete(id));
    }
}
