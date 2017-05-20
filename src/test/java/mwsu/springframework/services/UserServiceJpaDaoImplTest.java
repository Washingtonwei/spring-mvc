package mwsu.springframework.services;

import mwsu.springframework.domain.*;
import mwsu.springframework.config.JpaIntegrationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
public class UserServiceJpaDaoImplTest {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setProductServicec(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testSaveOfUser() throws Exception{
        User user = new User();

        user.setUsername("weibingyang");
        user.setPassword("mypassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password:");
        System.out.println(savedUser.getEncryptedPassword());
    }

    @Test
    public void testSaveOfUserWithCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Bingyang");
        customer.setLastName("Wei");

        User user = new User();
        user.setUsername("weibingyang");
        user.setPassword("mypassword");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCustomer()!=null;
        assert savedUser.getCustomer().getId()!=null;
    }

    @Test
    public void testAddCartToUser() throws Exception{
        User user = new User();
        user.setUsername("weibingyang");
        user.setPassword("mypassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart()!=null;
        assert savedUser.getCart().getId()!=null;
    }

    @Test
    public void testAddCartToUserWIthCartDetails() throws Exception{
        User user = new User();
        user.setUsername("weibingyang");
        user.setPassword("mypassword");

        user.setCart(new Cart());



        List<Product>  storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        CartDetail cartItemThree = new CartDetail();
        cartItemThree.setProduct(storedProducts.get(2));
        user.getCart().addCartDetail(cartItemThree);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart()!=null;
        assert savedUser.getCart().getId()!=null;
        assert savedUser.getCart().getCartDetails().size() == 3;
    }

    @Test
    public void testAddAndRemoveCartToUserWIthCartDetails() throws Exception{
        User user = new User();
        user.setUsername("weibingyang");
        user.setPassword("mypassword");

        user.setCart(new Cart());

        List<Product>  storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        CartDetail cartItemThree = new CartDetail();
        cartItemThree.setProduct(storedProducts.get(2));
        user.getCart().addCartDetail(cartItemThree);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getCart().getCartDetails().size() == 3;

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        savedUser = userService.saveOrUpdate(savedUser);

        assert savedUser.getCart().getCartDetails().size() == 2;

    }

}
