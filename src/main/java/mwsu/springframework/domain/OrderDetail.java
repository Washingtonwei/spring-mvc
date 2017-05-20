package mwsu.springframework.domain;

import javax.persistence.*;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@Entity
public class OrderDetail extends AbstractDomainObject{

    @ManyToOne
    private Order order;

    @OneToOne
    private Product product;

    private Integer quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
