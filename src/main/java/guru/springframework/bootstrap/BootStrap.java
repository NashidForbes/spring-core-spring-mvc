package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.CustomerService;
import guru.springframework.services.interfaces.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BootStrap implements CommandLineRunner {

    private final ProductService productService;
    private final CustomerService customerService;

    public BootStrap(ProductService productService,
                     CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
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

        customer1.setAddressLine1("5 Some new street");
        customer1.setAddressLine2("Level 9");
        customer1.setCity("Acapulco");
        customer1.setEmail("customer1@email.com");
        customer1.setFirstName("Edwin");
        customer1.setLastName("Smake");
        customer1.setPhoneNumber("434-433-3434");
        customer1.setState("NC");
        customer1.setZipCode("3432PZ");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();

        customer2.setAddressLine1("13434 Epsilon st.");
        customer2.setAddressLine2("Level 50");
        customer2.setCity("Windsor");
        customer2.setEmail("customer2@email.com");
        customer2.setFirstName("Gegan");
        customer2.setLastName("Norson");
        customer2.setPhoneNumber("744-833-5666");
        customer2.setState("FL");
        customer2.setZipCode("3432PZ");
        customerService.saveOrUpdate(customer2);

    }

}
