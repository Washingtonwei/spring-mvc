package mwsu.springframework.services.reposervices;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.converters.ProductFormToProduct;
import mwsu.springframework.converters.ProductToProductForm;
import mwsu.springframework.domain.Product;
import mwsu.springframework.repositories.ProductRepository;
import mwsu.springframework.services.ProductService;
import mwsu.springframework.services.SendTextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingyang.wei on 5/10/2017.
 */
@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;
    private ProductToProductForm productToProductForm;
    private SendTextMessageService sendTextMessageService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }
    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }
    @Autowired
    public void setSendTextMessageService(SendTextMessageService sendTextMessageService) {
        this.sendTextMessageService = sendTextMessageService;
    }

    @Override
    public List<?> listAll() {

        sendTextMessageService.sendTextMessage("List Products!");
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        sendTextMessageService.sendTextMessage("Requested Product ID: " + id);
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
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
