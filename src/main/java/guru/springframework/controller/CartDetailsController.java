package guru.springframework.controller;

import guru.springframework.domain.CartDetail;
import guru.springframework.services.interfaces.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class CartDetailsController {
    private CartDetailService cartDetailService;

    @Autowired
    public void setCartDetailService(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @RequestMapping({"/cartdetails", "/cartdetail/list"})
    public String listAllCartDetails(Model Model) {
        List<CartDetail> allCartDetails = (List<CartDetail>) cartDetailService.listAll();
        Model.addAttribute("cartdetails", allCartDetails);
        return "cartdetails/cartdetails";

    }

    @RequestMapping("/cartdetail/update/{id}")
    public String editCartDetail(@PathVariable String id, Model model) {
        CartDetail cartDetail = cartDetailService.getById(Integer.parseInt(id));
        model.addAttribute("cartdetail", cartDetail);
        return "cartdetails/cartdetailform";
    }


    @RequestMapping("/cartdetail/get/{id}")
    public String findCartDetailById(@PathVariable String id, Model Model) {
        CartDetail cartDetail = cartDetailService.getById(Integer.parseInt(id));
        Model.addAttribute("cartdetail", cartDetail);
        return "cartdetails/cartdetail";

    }

    @RequestMapping("/cartdetail/new")
    public String newCartDetail(Model model) {
        model.addAttribute("cartdetail", new CartDetail());
        return "cartdetails/cartdetailform";
    }

/*    @RequestMapping(value = "/cartdetail", method = RequestMethod.POST)
    public String saveOrUpdateCartDetail(CartDetail cartDetail) {
        CartDetail savedCartDetail = cartDetailService.saveOrUpdate(cartDetail);
        return "redirect:/v1/cartdetail/get/" + savedCartDetail.getId();
    }*/

    // not functional, cascade type bounded to User object, merge and persist operations only.
    @RequestMapping(value = "/cartdetail/delete", method = RequestMethod.POST)
    public String deleteCartDetail(@ModelAttribute("cartdetail") CartDetail cartDetail) {
        Integer cartId = cartDetail.getCart().getId();
        cartDetailService.deleteById(cartDetail.getId());
        return "redirect:/v1/cart/get/" + cartId;
    }


}
