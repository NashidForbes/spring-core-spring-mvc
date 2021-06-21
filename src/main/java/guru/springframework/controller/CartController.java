package guru.springframework.controller;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class CartController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping({"/carts", "/cart/list"})
    public String listAllCarts(Model Model) {
        List<Cart> allCarts = (List<Cart>) cartService.listAll();
        Model.addAttribute("carts", allCarts);
        return "carts/carts";

    }

    @RequestMapping("/cart/update/{id}")
    public String editCart(@PathVariable String id, Model model) {
        Cart cart = cartService.getById(Integer.parseInt(id));
        model.addAttribute("cart", cart);
        return "carts/cartform";
    }


    @RequestMapping("/cart/get/{id}")
    public String findCartById(@PathVariable String id, Model Model) {
        Cart cart = cartService.getById(Integer.parseInt(id));
        List<CartDetail> allCartDetails = (List<CartDetail>) cartService.listAllCartDetailsById(id);

        Model.addAttribute("cartdetails", allCartDetails);
        Model.addAttribute("cart", cart);
        return "carts/cart";

    }

    @RequestMapping("/cart/new")
    public String newCart(Model model) {
        model.addAttribute("cart", new Cart());
        return "carts/cartform";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String saveOrUpdateCart(Cart cart) {
        Cart savedCart = cartService.saveOrUpdate(cart);
        return "redirect:/v1/cart/get/" + savedCart.getId();
    }

    // not functional, cascade type bounded to User object, merge and persist operations only.
    @RequestMapping(value = "/cart/delete", method = RequestMethod.POST)
    public String editCart(Cart cart) {
        cartService.deleteById(cart.getId());
        return "redirect:/v1/cart";
    }
}
