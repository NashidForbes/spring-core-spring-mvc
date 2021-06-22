package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.domain.security.Role;
import guru.springframework.enums.OrderStatus;
import guru.springframework.services.interfaces.*;
import guru.springframework.services.security.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

// can use the BootStrap class with MapServices or JPADao implementation services
@Component
public class BootStrap implements CommandLineRunner {

    private final ProductService productService;
    private final CustomerService customerService;
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;
    private final RoleService roleService;

    public BootStrap(ProductService productService,
                     CustomerService customerService,
                     UserService userService,
                     OrderService orderService,
                     OrderLineService orderLineService,
                     CartService cartService,
                     RoleService roleService) {
        this.productService = productService;
        this.customerService = customerService;
        this.userService = userService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    private void loadData() {
        loadRoles();
        loadProductsData();
        loadUserCustomersData();
        assignUsersToDefaultRole();
        loadUserCartData();
        loadOrderData();
    }

    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    private void loadProductsData() {
        Product product1 = new Product();
        product1.setDescription("New product1");
        product1.setImageUrl("http://product1.com/image_url/image.png");
        product1.setPrice(new BigDecimal(10.00));
        productService.saveOrUpdate(product1);
        Product product2 = new Product();
        product2.setDescription("New product2");
        product2.setImageUrl("http://product2.com/image_url/image.png");
        product2.setPrice(new BigDecimal(10.00));
        productService.saveOrUpdate(product2);
        Product product3 = new Product();
        product3.setDescription("New product3");
        product3.setImageUrl("http://product3.com/image_url/image.png");
        product3.setPrice(new BigDecimal(10.00));
        productService.saveOrUpdate(product3);
        Product product4 = new Product();
        product4.setDescription("New product4");
        product4.setImageUrl("http://product4.com/image_url/image.png");
        product4.setPrice(new BigDecimal(10.00));
        productService.saveOrUpdate(product4);

    }

    private void loadUserCustomersData() {
        Customer customer1 = new Customer();
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password");
        customer1.setUser(user1);
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("5 Some new street");
        customer1.getBillingAddress().setAddressLine2("Level 9");
        customer1.getBillingAddress().setCity("Acapulco");
        customer1.setEmail("customer1@email.com");
        customer1.setFirstName("Edwin");
        customer1.setLastName("Smake");
        customer1.setPhoneNumber("434-433-3434");
        customer1.getBillingAddress().setState("NC");
        customer1.getBillingAddress().setZipCode("3432PZ");
        customer1.setShippingAddress(new Address());
        customer1.getShippingAddress().setAddressLine1("5 Some new street");
        customer1.getShippingAddress().setAddressLine2("Level 9");
        customer1.getShippingAddress().setCity("Acapulco");
        customer1.getShippingAddress().setState("NC");
        customer1.getShippingAddress().setZipCode("3432PZ");
        user1.setCustomer(customer1);
        user1.updateTimestamps();
        //Can use either userService or CustomerService
        //since the relationship between User object and Customer object
        //is bi-directional (also need same cascade types between the two
        // to prevent transient exception).
        //userService.saveOrUpdate(user1);
        customerService.saveOrUpdate(customer1);
        Customer customer2 = new Customer();
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("13434 Epsilon st.");
        customer2.getBillingAddress().setAddressLine2("Level 50");
        customer2.getBillingAddress().setCity("Windsor");
        customer2.setEmail("customer2@email.com");
        customer2.setFirstName("Gegan");
        customer2.setLastName("Norson");
        customer2.setPhoneNumber("744-833-5666");
        customer2.getBillingAddress().setState("FL");
        customer2.getBillingAddress().setZipCode("3432PZ");
        customer2.setShippingAddress(new Address());
        customer2.getShippingAddress().setAddressLine1("13434 Epsilon st.");
        customer2.getShippingAddress().setAddressLine2("Level 50");
        customer2.getShippingAddress().setCity("Windsor");
        customer2.getShippingAddress().setState("FL");
        customer2.getShippingAddress().setZipCode("3432PZ");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password");
        user2.setCustomer(customer2);
        user2.updateTimestamps();
        customer2.setUser(user2);
        //Can use either userService or CustomerService
        //since the relationship between User object and Customer object
        //is bi-directional (also need same cascade types between the two
        // to prevent transient exception).
        //userService.saveOrUpdate(user2);
        customerService.saveOrUpdate(customer2);

    }

    private void loadUserCartData() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();
        User user1 = users.get(0);
        User user2 = users.get(1);
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.setQuantity(3);
        cart2.setQuantity(4);
        cart1.setUser(user1);
        //user1.setCart(cart1);
        cart2.setUser(user2);
        //user2.setCart(cart2);



        CartDetail cartDetail1 = new CartDetail();
        CartDetail cartDetail2 = new CartDetail();
        CartDetail cartDetail3 = new CartDetail();
        CartDetail cartDetail4 = new CartDetail();
        CartDetail cartDetail5 = new CartDetail();
        CartDetail cartDetail6 = new CartDetail();
        cartDetail1.setProduct(products.get(0));
        cartDetail2.setProduct(products.get(1));
        cartDetail3.setProduct(products.get(2));
        cartDetail4.setProduct(products.get(3));
        cartDetail5.setProduct(products.get(2));
        cartDetail6.setProduct(products.get(3));

        cart1.addCartDetail(cartDetail1);
        cart1.addCartDetail(cartDetail2);
        cart1.addCartDetail(cartDetail3);
        cart1.addCartDetail(cartDetail4);

        cart2.addCartDetail(cartDetail5);
        cart2.addCartDetail(cartDetail6);

        userService.saveOrUpdate(user1);
        userService.saveOrUpdate(user2);
        cartService.saveOrUpdate(cart1);
        cartService.saveOrUpdate(cart2);
    }

    private void loadOrderData() {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        List<Product> products = (List<Product>) productService.listAll();
        
        Order order1 = new Order();
        order1.setShipped(new Date());
        order1.setCustomer(customers.get((0)));

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setProduct(products.get(0));
        orderLine1.setQuantityOrdered(10L);
        orderLine1.setOrder(order1);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setProduct(products.get(1));
        orderLine2.setQuantityOrdered(20L);
        orderLine2.setOrder(order1);

        OrderLine orderLine3 = new OrderLine();
        orderLine3.setProduct(products.get(2));
        orderLine3.setQuantityOrdered(50L);
        orderLine3.setOrder(order1);

        OrderLine orderLine4 = new OrderLine();
        orderLine4.setProduct(products.get(3));
        orderLine4.setQuantityOrdered(50L);
        orderLine4.setOrder(order1);

        order1.addToOrderLines(orderLine1);
        order1.addToOrderLines(orderLine2);
        order1.addToOrderLines(orderLine3);
        order1.addToOrderLines(orderLine4);

        order1.setOrderStatus(OrderStatus.SHIPPED);
        orderService.saveOrUpdate(order1);

        Order order2 = new Order();
        order2.setShipped(new Date());
        order2.setCustomer(customers.get((1)));

        OrderLine orderLine5 = new OrderLine();
        orderLine5.setProduct(products.get(2));
        orderLine5.setQuantityOrdered(50L);
        orderLine5.setOrder(order2);

        OrderLine orderLine6 = new OrderLine();
        orderLine6.setProduct(products.get(3));
        orderLine6.setQuantityOrdered(50L);
        orderLine6.setOrder(order2);

        order2.addToOrderLines(orderLine5);
        order2.addToOrderLines(orderLine6);

        order2.setOrderStatus(OrderStatus.SHIPPED);
        orderService.saveOrUpdate(order2);

    }
    
}
