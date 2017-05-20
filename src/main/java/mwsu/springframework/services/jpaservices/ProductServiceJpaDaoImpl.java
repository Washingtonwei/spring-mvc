package mwsu.springframework.services.jpaservices;

import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.converters.ProductFormToProduct;
import mwsu.springframework.converters.ProductToProductForm;
import mwsu.springframework.domain.Product;
import mwsu.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by bingyang.wei on 5/7/2017.
 */
@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl implements ProductService {
    private EntityManagerFactory emf;
    private ProductToProductForm productToProductForm;
    private ProductFormToProduct productFormToProduct;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }
    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product savedProduct = em.merge(product);
        em.getTransaction().commit();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Product.class,id));
        em.getTransaction().commit();
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
