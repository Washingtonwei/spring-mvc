package mwsu.springframework.converters;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by bingyang.wei on 5/12/2017.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product>{
    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();
        product.setId(productForm.getId());
        product.setVersion(productForm.getVersion());
        product.setDescription(productForm.getDescription());
        product.setImageUrl(productForm.getImageUrl());
        product.setPrice(productForm.getPrice());
        return product;
    }
}
