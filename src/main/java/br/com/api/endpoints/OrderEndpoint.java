package br.com.api.endpoints;

import br.com.api.endpoints.dto.ItemRequest;
import br.com.api.endpoints.dto.OrderRequest;
import br.com.api.entity.Customer;
import br.com.api.entity.Item;
import br.com.api.entity.Order;
import br.com.api.services.CustomerService;
import br.com.api.services.ItemService;
import br.com.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static br.com.api.entity.builders.ItemBuilder.itemBuilder;
import static br.com.api.entity.builders.OrderBuilder.orderBuilder;

@RestController
@RequestMapping(value = "/rs/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderEndpoint {

    @Autowired
    private OrderService service;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(HttpServletRequest request){
        Customer customer = customerService.findByName(request.getUserPrincipal().getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createToCustomer(customer));
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<Order> update(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest, HttpServletRequest request){
        Customer customer = customerService.findByName(request.getUserPrincipal().getName());
        Order order = orderBuilder()
                .withId(orderId)
                .withCustomer(customer)
                .withStatus(orderRequest.getStatus())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.update(order));
    }

    @GetMapping(value = "/{orderId}/items")
    public ResponseEntity<List<Item>> findByOrderId(@PathVariable Long orderId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.findByOrderId(orderId));
    }

    @PutMapping(value = "/{orderId}/items")
    public ResponseEntity<Item> addItemInOrder(@PathVariable Long orderId, @RequestBody ItemRequest itemRequest, HttpServletRequest request){
        Customer customer = customerService.findByName(request.getUserPrincipal().getName());
        Item item = itemBuilder()
                .withOrder(new Order(orderId, customer))
                .withProductId(itemRequest.getProductId())
                .withQuantity(itemRequest.getQuantity())
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.save(item));
    }

    @DeleteMapping(value = "/{orderId}/items/{itemId}")
    public ResponseEntity<Item> removeItemInOrder(@PathVariable Long orderId, @PathVariable  Long itemId, HttpServletRequest request){
        Customer customer = customerService.findByName(request.getUserPrincipal().getName());
        Order order = new Order(orderId, customer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemService.delete(new Item(itemId, order)));
    }
}
