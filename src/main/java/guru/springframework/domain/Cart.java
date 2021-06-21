package guru.springframework.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends AbstractDomainClass {
    @OneToOne
    private User user;

    // mappedBy the carDetails will use a foreign key instead of a join table.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

    private Integer quantity;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.setCart(this);
        this.user = user;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void addCartDetail(CartDetail cartDetail) {
        cartDetails.add(cartDetail);
        cartDetail.setCart(this);
    }

    public void removeCartDetail(CartDetail cartDetail) {
        cartDetail.setCart(null);
        this.cartDetails.remove(cartDetail);
    }

}
