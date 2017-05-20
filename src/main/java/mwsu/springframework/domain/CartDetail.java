package mwsu.springframework.domain;

import javax.persistence.*;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@Entity
public class CartDetail extends AbstractDomainObject{

    private Integer quantity;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
