package mwsu.springframework.services;

import mwsu.springframework.domain.Product;
import mwsu.springframework.config.JpaIntegrationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by bingyang.wei on 5/8/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
public class ProductServiceJapDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Test
    public void testListMethod() throws Exception{
        List<Product> products = (List<Product>)productService.listAll();
        assert products.size() == 5;
    }
}
