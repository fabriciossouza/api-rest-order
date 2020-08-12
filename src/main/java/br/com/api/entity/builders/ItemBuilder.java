package br.com.api.entity.builders;

import br.com.api.entity.Item;
import br.com.api.entity.Order;
import br.com.api.entity.Product;

public class ItemBuilder {

    private Item item = new Item();

    private ItemBuilder(){

    }

    public static ItemBuilder itemBuilder() {
        return new ItemBuilder();
    }

    public ItemBuilder withQuantity(Integer quantity){
        item.setQuantity(quantity);
        return this;
    }

    public ItemBuilder withProductId(Long productId){
        item.setProduct(new Product(productId));
        return this;
    }

    public ItemBuilder withOrder(Order order){
        item.setOrder(order);
        return this;
    }

    public Item build(){
        return item;
    }
}
