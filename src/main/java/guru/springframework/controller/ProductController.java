package guru.springframework.controller;

import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listAllProducts(Model Model) {
        List<Product> allProducts = productService.listAllProducts();
        Model.addAttribute("allProducts", allProducts);
        return "products";

    }

    @RequestMapping("/products/{id}")
    public String findProductById(@PathVariable String id, Model Model) {
        Product product = productService.getById(Integer.parseInt(id));
        Model.addAttribute("product", product);
        return "product";

    }

}
