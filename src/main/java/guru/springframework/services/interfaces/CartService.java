package guru.springframework.services.interfaces;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;

import java.util.List;

public interface CartService extends CRUDService<Cart> {
    List<CartDetail> listAllCartDetailsById(String id);

}
