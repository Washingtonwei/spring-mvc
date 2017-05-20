package mwsu.springframework.domain;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@Entity
public class Cart extends AbstractDomainObject{

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public void addCartDetail(CartDetail cartDetail){
        cartDetails.add(cartDetail);
        cartDetail.setCart(this);
    }
    public void removeCartDetail(CartDetail cartDetail){
        cartDetail.setCart(null);
        cartDetails.remove(cartDetail);
    }
}
