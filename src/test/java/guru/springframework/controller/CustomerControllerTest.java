package guru.springframework.controller;

import guru.springframework.domain.Customer;
import guru.springframework.services.interfaces.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {
    @Mock //Mockito Mock object
    private CustomerService customerService;

    @InjectMocks //setups up controller, and injects mock objects into it.
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); //initilizes controller and mocks
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        customers.put(1, new Customer());
        customers.put(2, new Customer());
        //specific Mockito interaction, tell stub to return list of customers
        when(customerService.listAll())
                .thenReturn((List)customers.values()); //need to
        // strip generics to
        // keep Mockito happy.
        mockMvc.perform(get("/v1/customers/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers"))
                .andExpect(model().attribute("allCustomers", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;
        //Tell Mockito stub to return new customer for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/v1/customer/get/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        //Tell Mockito stub to return new customer for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());
        mockMvc.perform(get("/v1/customer/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        Integer id = 1;
        //should not call service
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/v1/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        Customer returnCustomer = new Customer();
        returnCustomer.setId(1);
        returnCustomer.setAddressLine1("5 Some new street");
        returnCustomer.setAddressLine2("Level 9");
        returnCustomer.setCity("Acapulco");
        returnCustomer.setEmail("returnCustomer@email.com");
        returnCustomer.setFirstName("Edwin");
        returnCustomer.setLastName("Smake");
        returnCustomer.setPhoneNumber("434-433-3434");
        returnCustomer.setState("NC");
        returnCustomer.setZipCode("3432PZ");
        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer);
        mockMvc.perform(post("/v1/customer")
                .param("id", returnCustomer.getId().toString())
                .param("addressLine1",  returnCustomer.getAddressLine1())
                .param("addressLine2", returnCustomer.getAddressLine2())
                .param("city", returnCustomer.getCity())
                .param("email", returnCustomer.getEmail())
                .param("firstName",  returnCustomer.getFirstName())
                .param("lastName", returnCustomer.getLastName())
                .param("phoneNumber", returnCustomer.getPhoneNumber())
                .param("state", returnCustomer.getState())
                .param("zipCode", returnCustomer.getZipCode()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/v1/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(returnCustomer.getId()))))

                .andExpect(model().attribute("customer", hasProperty("addressLine1",
                        is(returnCustomer.getAddressLine1()))))
                .andExpect(model().attribute("customer", hasProperty("addressLine2", is(returnCustomer.getAddressLine2()))))
                .andExpect(model().attribute("customer", hasProperty("city", is(returnCustomer.getCity()))))
                .andExpect(model().attribute("customer", hasProperty("email", is(returnCustomer.getEmail()))))
                .andExpect(model().attribute("customer", hasProperty("firstName",  is(returnCustomer.getFirstName()))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(returnCustomer.getLastName()))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(returnCustomer.getPhoneNumber()))))
                .andExpect(model().attribute("customer", hasProperty("state", is(returnCustomer.getState()))))
                .andExpect(model().attribute("customer", hasProperty("zipCode",
                        is(returnCustomer.getZipCode()))));
        //verify properties of bound object
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());
        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(returnCustomer.getAddressLine1(), boundCustomer.getValue().getAddressLine1());
        assertEquals(returnCustomer.getAddressLine2(), boundCustomer.getValue().getAddressLine2());
        assertEquals(returnCustomer.getCity(), boundCustomer.getValue().getCity());
        assertEquals(returnCustomer.getEmail(), boundCustomer.getValue().getEmail());
        assertEquals(returnCustomer.getFirstName(), boundCustomer.getValue().getFirstName());
        assertEquals(returnCustomer.getLastName(), boundCustomer.getValue().getLastName());
        assertEquals(returnCustomer.getPhoneNumber(), boundCustomer.getValue().getPhoneNumber());
        assertEquals(returnCustomer.getState(), boundCustomer.getValue().getState());
        assertEquals(returnCustomer.getZipCode(), boundCustomer.getValue().getZipCode());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        mockMvc.perform(post("/v1/customer/delete")
                .param("id", String.valueOf(id))
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/v1/customers"));
        verify(customerService, times(1)).deleteById(id);
    }
}