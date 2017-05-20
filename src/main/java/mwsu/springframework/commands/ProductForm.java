package mwsu.springframework.commands;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Created by bingyang.wei on 5/12/2017.
 */
public class ProductForm {
    private Integer id;
    private Integer version;

    @NotEmpty
    @Size(min = 5, max = 200)
    private String description;

    @NotNull
    @DecimalMin("0")
    @DecimalMax("5000")
    @Digits(integer = 6, fraction = 5)
    private BigDecimal price;

    @NotNull
    @URL
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
