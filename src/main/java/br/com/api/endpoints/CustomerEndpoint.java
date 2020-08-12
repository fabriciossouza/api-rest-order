package br.com.api.endpoints;

import br.com.api.endpoints.validator.UserValidator;
import br.com.api.entity.Customer;
import br.com.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rs/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerEndpoint {

    @Autowired
    private CustomerService service;
    @Autowired
    private UserValidator validator;

    @GetMapping
    public List<Customer> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        validator.validate(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(customer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.delete(id));
    }
}
