package br.com.api.services;

import br.com.api.entity.Customer;
import br.com.api.infrastructure.cache.AppCacheConfiguration;
import br.com.api.infrastructure.exceptions.BusinessException;
import br.com.api.infrastructure.exceptions.EntityNotFoundException;
import br.com.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.api.infrastructure.cache.DefaultCache.DEFAULT_CACHE_NAME;
import static br.com.api.infrastructure.cache.ShortTermCache.SHORT_TERM_CACHE_NAME;

@Service
@CacheConfig(cacheNames = SHORT_TERM_CACHE_NAME, keyGenerator = AppCacheConfiguration.DEFAULT_KEY_GENERATOR)
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Cacheable
    public Customer findByName(String userName){
        return repository.findByName(userName);
    }

    public Customer findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer Not Found!"));
    }

    public Customer save(Customer customer){
        Customer emailFound = repository.findByEmail(customer.getEmail());
        if(emailFound != null){
            throw new BusinessException("Email " + customer.getEmail() + " already existing!");
        }
        return repository.save(customer);
    }

    public Customer delete(Long id){
        Customer customer = findById(id);
        repository.delete(customer);
        return customer;
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }
}
