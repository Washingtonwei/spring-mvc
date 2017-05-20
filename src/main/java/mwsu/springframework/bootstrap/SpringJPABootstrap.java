package mwsu.springframework.bootstrap;

import mwsu.springframework.domain.*;
import mwsu.springframework.domain.security.Role;
import mwsu.springframework.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bingyang.wei on 5/7/2017.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private ProductService productService;
    private CustomerService customerService;
    private UserService userService;
    private OrderService orderService;
    private RoleService roleService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
        assignAdminToUser();
    }

    private void loadCarts() {
//        List<User> users = (List<User>)userService.listAll();
//        List<Product> products = (List<Product>)productService.listAll();
//
//        users.forEach(user -> {
//            user.setCart(new Cart());
//            CartDetail cartDetail = new CartDetail();
//            cartDetail.setProduct(products.get(0));
//            cartDetail.setQuantity(5);
//            user.getCart().addCartDetail(cartDetail);
//            userService.saveOrUpdate(user);
//        });
    }

    private void loadOrderHistory() {
//        List<User> users = (List<User>)userService.listAll();
//        List<Product> products = (List<Product>)productService.listAll();
//
//        users.forEach(user -> {
//            Order order = new Order();
//            order.setCustomer(user.getCustomer());
//            order.setOrderStatus(OrderStatus.SHIPPED);
//
//            products.forEach(product -> {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setProduct(product);
//                orderDetail.setQuantity(1);
//                order.addToOrderDetails(orderDetail);
//
//            });
//        });
        
    }

    public void loadProducts(){
        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);
    }
    public void loadUsersAndCustomers(){
        User user1 = new User();
        user1.setUsername("weibingyang");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Bingyang");
        customer1.setLastName("Wei");
        customer1.setEmail("weibingyang@gmail.com");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("6208 Talon Trl");
        customer1.getBillingAddress().setCity("Wichita Falls");
        customer1.getBillingAddress().setState("TX");
        customer1.getBillingAddress().setZipCode("76310");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("yumengyuan");
        user2.setPassword("password");
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Mengyuan");
        customer2.setLastName("Yu");
        customer2.setEmail("ymo0329@gmail.com");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("6208 Talon Trl");
        customer2.getBillingAddress().setCity("Wichita Falls");
        customer2.getBillingAddress().setState("TX");
        customer2.getBillingAddress().setZipCode("76310");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("weichangjiang");
        user3.setPassword("password");
        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Changjiang");
        customer3.setLastName("Wei");
        customer3.setEmail("weichangjiang@gmail.com");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("6208 Talon Trl");
        customer3.getBillingAddress().setCity("Wichita Falls");
        customer3.getBillingAddress().setState("TX");
        customer3.getBillingAddress().setZipCode("76310");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);

        User user4 = new User();
        user4.setUsername("fushaoxia");
        user4.setPassword("password");
        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setFirstName("Shaoxia");
        customer4.setLastName("Fu");
        customer4.setEmail("fshaoxia@gmail.com");
        customer4.setBillingAddress(new Address());
        customer4.getBillingAddress().setAddressLine1("6208 Talon Trl");
        customer4.getBillingAddress().setCity("Wichita Falls");
        customer4.getBillingAddress().setState("TX");
        customer4.getBillingAddress().setZipCode("76310");
        user4.setCustomer(customer4);
        userService.saveOrUpdate(user4);

    }

    private void loadRoles(){
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);

    }

    private void assignUsersToDefaultRole(){
        List<Role> roles = (List<Role>)roleService.listAll();
        List<User> users = (List<User>)userService.listAll();

        roles.forEach(role->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user->{
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
//            if(role.getRole().equalsIgnoreCase("ADMIN")){
//                users.forEach(user->{
//                    if(user.getUsername().equals("weibingyang")){
//                        user.addRole(role);
//                        userService.saveOrUpdate(user);
//                    }
//                });
//            }
        });
    }
    private void assignAdminToUser(){
        List<Role> roles = (List<Role>)roleService.listAll();
        List<User> users = (List<User>)userService.listAll();
        roles.forEach(role-> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("weibingyang")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}
