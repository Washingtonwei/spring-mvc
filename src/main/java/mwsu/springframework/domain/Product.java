package mwsu.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@Entity
public class Product extends AbstractDomainObject{

    private String description;
    private BigDecimal price;
    private String imageUrl;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
