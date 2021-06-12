package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Customer;
import guru.springframework.services.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadAllCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<Customer>(customers.values());
    }

    @Override
    public Customer getById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(getNextId());
        }
        customers.put(customer.getId(), customer);
        return customers.get(customer.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Customers initial size: " + String.valueOf(customers.size()));
        customers.remove(id);
        log.info("Deleted Customer id: " + id);
        log.info("Customers post-delete size: " + String.valueOf(customers.size()));
    }

    private void loadAllCustomers() {
        customers = new HashMap<Integer, Customer>();
        
        Customer customer1 = new Customer();
        customer1.setId(getNextId());
        customer1.setAddressLine1("5 Some new street");
        customer1.setAddressLine2("Level 9");
        customer1.setCity("Acapulco");
        customer1.setEmail("customer1@email.com");
        customer1.setFirstName("Edwin");
        customer1.setLastName("Smake");
        customer1.setPhoneNumber("434-433-3434");
        customer1.setState("NC");
        customer1.setZipCode("3432PZ");
        
        customers.put(customer1.getId(), customer1);

        Customer customer2 = new Customer();
        customer2.setId(getNextId());
        customer2.setAddressLine1("13434 Epsilon st.");
        customer2.setAddressLine2("Level 50");
        customer2.setCity("Windsor");
        customer2.setEmail("customer2@email.com");
        customer2.setFirstName("Gegan");
        customer2.setLastName("Norson");
        customer2.setPhoneNumber("744-833-5666");
        customer2.setState("FL");
        customer2.setZipCode("3432PZ");

        customers.put(customer2.getId(), customer2);

    }

    private Integer getNextId() {
        Integer nextInt = customers.size() + 1;
        return nextInt;
    }
}
