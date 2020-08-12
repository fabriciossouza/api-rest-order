package br.com.api.services;

import br.com.api.entity.Customer;
import br.com.api.entity.Order;
import br.com.api.entity.Status;
import br.com.api.entity.builders.OrderBuilder;
import br.com.api.infrastructure.exceptions.EntityNotFoundException;
import br.com.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public Order findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order Not Found!"));
    }

    public Order createToCustomer(Customer customer){
        Order order = OrderBuilder.orderBuilder()
                .withCustomer(customer)
                .withStatus(Status.INITIAL)
                .withSession(UUID.randomUUID().toString())
                .build();
        return repository.save(order);
    }

    public Order update(Order order){
        Order orderFound = findById(order.getId());
        orderFound.setStatus(order.getStatus());
        Order orderUpdated = repository.save(orderFound);
        return orderUpdated;
    }

    public Order delete(Long id){
        Order order = findById(id);
        repository.delete(order);
        return order;
    }

    public List<Order> findAll(){
        return repository.findAll();
    }
}
