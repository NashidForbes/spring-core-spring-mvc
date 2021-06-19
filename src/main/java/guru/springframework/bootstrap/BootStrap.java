package guru.springframework.bootstrap;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.services.interfaces.CustomerService;
import guru.springframework.services.interfaces.ProductService;
import guru.springframework.services.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// can use the BootStrap class with MapServices or JPADao implementation services
@Component
public class BootStrap implements CommandLineRunner {

    private final ProductService productService;
    private final CustomerService customerService;
    private final UserService userService;

    public BootStrap(ProductService productService,
                     CustomerService customerService,
                     UserService userService) {
        this.productService = productService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    private void loadData(){
            loadProductsData();
            loadCustomersData();
    }

    private void loadProductsData(){
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

    private void loadCustomersData(){
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

}
