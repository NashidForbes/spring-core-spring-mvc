package guru.springframework.services.interfaces;

import guru.springframework.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product getById(Integer id);

    Product saveOrUpdate(Product product);

    void deleteById(Integer id);
}
