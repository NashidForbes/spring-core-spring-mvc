package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import guru.springframework.services.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    public CustomerServiceImpl() {
        loadAllCustomers();
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(getNextId());
        }
        domainMap.put(customer.getId(), customer);
        return (Customer) domainMap.get(customer.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Customers initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted Customer id: " + id);
        log.info("Customers post-delete size: " + String.valueOf(domainMap.size()));
    }

    private void loadAllCustomers() {
        domainMap = new HashMap<Integer, DomainObject>();
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
        domainMap.put(customer1.getId(), customer1);
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
        domainMap.put(customer2.getId(), customer2);

    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {
        loadAllCustomers();
    }
}
