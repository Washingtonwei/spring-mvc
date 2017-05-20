package mwsu.springframework.services.mapservices;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.converters.ProductFormToProduct;
import mwsu.springframework.converters.ProductToProductForm;
import mwsu.springframework.domain.DomainObject;
import mwsu.springframework.domain.Product;
import mwsu.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    private ProductFormToProduct productFormToProduct;
    private ProductToProductForm productToProductForm;

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }
    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Product saveOrUpdateForm(ProductForm productForm) {

        if(productForm.getId() != null){//existing product
            Product productToUpdate = this.getById(productForm.getId());
            productToUpdate.setVersion(productForm.getVersion());
            productToUpdate.setDescription(productForm.getDescription());
            productToUpdate.setPrice(productForm.getPrice());
            productToUpdate.setImageUrl(productForm.getImageUrl());

            return saveOrUpdate(productToUpdate);
        }

        Product newProduct = productFormToProduct.convert(productForm);
        return saveOrUpdate(newProduct);
    }

    @Override
    public ProductForm getForm(Product product) {
        return productToProductForm.convert(product);
    }

}
