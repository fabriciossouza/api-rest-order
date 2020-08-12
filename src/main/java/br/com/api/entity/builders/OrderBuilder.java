package br.com.api.entity.builders;

import br.com.api.entity.*;
import br.com.api.entity.Order;

import java.util.Calendar;

public class OrderBuilder {

    private Order order = new Order();

    private OrderBuilder(){
        order.setDate(Calendar.getInstance());
    }

    public static OrderBuilder orderBuilder() {
        return new OrderBuilder();
    }

    public OrderBuilder withId(Long id){
        order.setId(id);
        return this;
    }

    public OrderBuilder withSession(String session){
        order.setSession(session);
        return this;
    }

    public OrderBuilder withStatus(Status status){
        order.setStatus(status);
        return this;
    }

    public OrderBuilder withCustomer(Customer customer){
        order.setCustomer(customer);
        return this;
    }

    public Order build(){
        return order;
    }
}
