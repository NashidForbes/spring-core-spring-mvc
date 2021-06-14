package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    public ProductServiceImpl() {
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

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {

    }
}
