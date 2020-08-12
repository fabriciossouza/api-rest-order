package br.com.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID")
    private Order order;

    @Column(name = "QUANTITY")
    private Integer quantity  = 1;

    @Column(name = "PRICE")
    private BigDecimal value  = BigDecimal.ZERO;

    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal = BigDecimal.ZERO;

    public Item() {
    }

    public Item(Long id, Order order) {
        this.id = id;
        this.order = order;
    }

    public Item(Product product, Integer quantity, Order order) {
        this.value = product.getPrice();
        this.quantity = quantity;
        this.order = order;
        this.subtotal = value.multiply(new BigDecimal(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
