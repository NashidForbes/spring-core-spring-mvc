package guru.springframework.controller;

import guru.springframework.domain.Customer;
import guru.springframework.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"/customers", "/customer/list"})
    public String listAllCustomers(Model Model) {
        List<Customer> allCustomers = (List<Customer>) customerService.listAll();
        Model.addAttribute("allCustomers", allCustomers);
        return "customers/customers";

    }

    @RequestMapping("/customer/update/{id}")
    public String editCustomer(@PathVariable String id, Model model) {
        Customer customer = customerService.getById(Integer.parseInt(id));
        model.addAttribute("customer", customer);
        return "customers/customerform";
    }


    @RequestMapping("/customer/get/{id}")
    public String findCustomerById(@PathVariable String id, Model Model) {
        Customer customer = customerService.getById(Integer.parseInt(id));
        Model.addAttribute("customer", customer);
        return "customers/customer";

    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer) {
        Customer savedCustomer = customerService.saveOrUpdate(customer);
        return "redirect:/v1/customer/get/" + savedCustomer.getId();
    }

    // not functional, cascade type bounded to User object, merge and persist operations only.
    @RequestMapping(value = "/customer/delete", method = RequestMethod.POST)
    public String editCustomer(Customer customer) {
        customerService.deleteById(customer.getId());
        return "redirect:/v1/customers";
    }
}
