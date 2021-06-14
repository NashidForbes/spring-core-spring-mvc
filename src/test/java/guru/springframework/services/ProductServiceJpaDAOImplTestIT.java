package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"}) // works independent of the applications.properties profile settings
public class ProductServiceJpaDAOImplTestIT {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void TestListMethod() throws Exception {

        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 4;
    }



}
