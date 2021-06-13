package guru.springframework.services;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    public ProductServiceImpl() {
        loadAllProdcts();
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(getNextId());
        }
        domainMap.put(product.getId(), product);
        return (Product) domainMap.get(product.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Products initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted Product id: " + id);
        log.info("Products post-delete size: " + String.valueOf(domainMap.size()));
    }

    private void loadAllProdcts() {
        domainMap = new HashMap<Integer, DomainObject>();
        Product product1 = new Product();
        product1.setId(getNextId());
        product1.setDescription("New product1");
        product1.setImageUrl("http://product1.com/image_url/image.png");
        product1.setPrice(new BigDecimal(10.00));
        domainMap.put(product1.getId(), product1);
        Product product2 = new Product();
        product2.setId(getNextId());
        product2.setDescription("New product2");
        product2.setImageUrl("http://product2.com/image_url/image.png");
        product2.setPrice(new BigDecimal(10.00));
        domainMap.put(product2.getId(), product2);
        Product product3 = new Product();
        product3.setId(getNextId());
        product3.setDescription("New product3");
        product3.setImageUrl("http://product3.com/image_url/image.png");
        product3.setPrice(new BigDecimal(10.00));
        domainMap.put(product3.getId(), product3);
        Product product4 = new Product();
        product4.setId(getNextId());
        product4.setDescription("New product4");
        product4.setImageUrl("http://product4.com/image_url/image.png");
        product4.setPrice(new BigDecimal(10.00));
        domainMap.put(product4.getId(), product4);
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {
        loadAllProdcts();
    }
}
