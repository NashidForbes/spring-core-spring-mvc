package guru.springframework.controller;

import guru.springframework.domain.Product;
import guru.springframework.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/products", "/product/list"})
    public String listAllProducts(Model Model) {
        List<Product> allProducts = (List<Product>) productService.listAll();
        Model.addAttribute("allProducts", allProducts);
        return "products";

    }

    @RequestMapping("/product/update/{id}")
    public String editProduct(@PathVariable String id, Model model){
        Product product = productService.getById(Integer.parseInt(id));

        model.addAttribute("product", product);

        return "productform";
    }


    @RequestMapping("/product/get/{id}")
    public String findProductById(@PathVariable String id, Model Model) { Product product = productService.getById(Integer.parseInt(id));
        Model.addAttribute("product", product);
        return "product";

    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());

        return "productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){
           Product savedProduct = productService.saveOrUpdate(product);

        return "redirect:/v1/products";
    }


    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public String deleteProduct(Product product){
        productService.deleteById(product.getId());

        return "redirect:/v1/products";
    }
}
