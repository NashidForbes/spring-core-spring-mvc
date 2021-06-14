package guru.springframework.services.mapservices;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import guru.springframework.services.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    public CustomerServiceImpl() {
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

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {

    }
}
