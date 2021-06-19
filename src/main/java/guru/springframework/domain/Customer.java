package guru.springframework.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Customer extends AbstractDomainClass {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billingAddress;

    // if User object deleted won't propagate down to Customer object.
    // only Merge and Persist cascade operations allowed.
    // protects against accidental deletion of User during CRUD delete operations
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // if User object deleted won't propagate down to Customer object.
    // only Merge and Persist cascade operations allowed.
    // protects against accidental deletion of Customer during CRUD delete operations
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
