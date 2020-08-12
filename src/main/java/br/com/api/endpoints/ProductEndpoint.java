package br.com.api.endpoints;

import br.com.api.endpoints.validator.ProductValidator;
import br.com.api.entity.Product;
import br.com.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rs/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductEndpoint {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductValidator validator;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        validator.validate(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(product));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.update(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.delete(id));
    }
}
