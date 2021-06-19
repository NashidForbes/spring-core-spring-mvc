package guru.springframework.domain;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User extends AbstractDomainClass{
    // if User object deleted won't propagate down to Customer object.
    // only Merge and Persist cascade operations allowed.
    // protects against accidental deletion of Customer during CRUD delete operations
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Customer customer;

    private String username;

/*    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;*/

    @Transient
    private String password;

    private String encryptedPassword;

    private Boolean enabled = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.setUser(this);
    }


/*    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }*/
}
