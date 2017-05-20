package mwsu.springframework.services;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.domain.Product;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
public interface ProductService extends CRUDService<Product>{
    Product saveOrUpdateForm(ProductForm productForm);
    ProductForm getForm(Product product);
}
