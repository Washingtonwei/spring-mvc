package mwsu.springframework.converters;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Created by bingyang.wei on 5/12/2017.
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setImageUrl(product.getImageUrl());
        productForm.setPrice(product.getPrice());
        productForm.setVersion(product.getVersion());
        return productForm;
    }
}
