package guru.springframework.services;

import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        loadAllProdcts();
    }

    @Override
    public List<Product> listAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getById(Integer id) {
        return products.get(id);
    }

    private void loadAllProdcts() {
        products = new HashMap<Integer, Product>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("New product1");
        product1.setImageUrl("http://product1.com/image_url/image.png");
        product1.setPrice(new BigDecimal(10.00));
        products.put(1, product1);
        Product product2 = new Product();
        product2.setId(1);
        product2.setDescription("New product2");
        product2.setImageUrl("http://product2.com/image_url/image.png");
        product2.setPrice(new BigDecimal(10.00));
        products.put(2, product2);
        Product product3 = new Product();
        product3.setId(1);
        product3.setDescription("New product3");
        product3.setImageUrl("http://product3.com/image_url/image.png");
        product3.setPrice(new BigDecimal(10.00));
        products.put(3, product3);
        Product product4 = new Product();
        product4.setId(1);
        product4.setDescription("New product4");
        product4.setImageUrl("http://product4.com/image_url/image.png");
        product4.setPrice(new BigDecimal(10.00));
        products.put(4, product4);
    }

}
