package br.com.api.services;

import br.com.api.entity.Item;
import br.com.api.infrastructure.exceptions.EntityNotFoundException;
import br.com.api.repository.ItemRepository;
import br.com.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    public Item findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item Not Found!"));
    }

    public Item save(Item item){
        item.setOrder(orderService.findById(item.getOrder().getId()));
        item.setProduct(productService.findById(item.getProduct().getId()));
        return repository.save(item);
    }

    public Item delete(Item item){
        Item itemFound = findById(item.getId());
        repository.delete(itemFound);
        return item;
    }

    public List<Item> findAll(){
        return repository.findAll();
    }

    public List<Item> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
