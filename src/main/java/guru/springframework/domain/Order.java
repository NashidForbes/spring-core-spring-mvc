package guru.springframework.domain;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {
    // mappedBy the orderLines will use a foreign key instead of a join table.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();
    @OneToOne
    private Customer customer;
    @Embedded
    private Address shippingToAddress;

    @Timestamp
    private Date shipped;

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingToAddress() {
        return shippingToAddress;
    }

    public void setShippingToAddress(Address shippingToAddress) {
        this.shippingToAddress = shippingToAddress;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }
}
