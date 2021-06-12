package guru.springframework.services.interfaces;

import guru.springframework.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomers();

    Customer getById(Integer id);

    Customer saveOrUpdate(Customer customer);

    void deleteById(Integer id);

}
