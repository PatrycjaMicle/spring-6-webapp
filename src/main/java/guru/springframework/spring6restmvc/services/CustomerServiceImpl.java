package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{


    private Map<UUID,Customer> customerMap;

    public CustomerServiceImpl(){

        Customer customer1= Customer.builder()
                .customerName("Nicu")
                .id(UUID.randomUUID())
                .version(123)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2= Customer.builder()
                .customerName("Vanessa")
                .id(UUID.randomUUID())
                .version(133)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3= Customer.builder()
                .customerName("Patrycja")
                .id(UUID.randomUUID())
                .version(113)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();


        this.customerMap=new HashMap<>();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);

    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer newCustomer=Customer.builder()
                .customerName(customer.getCustomerName())
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(newCustomer.getId(),newCustomer);

        return newCustomer;
    }

    @Override
    public Customer updateCustomerById(UUID id,Customer customer) {

        Customer updatedCustomer = customerMap.get(id);
        updatedCustomer.setCustomerName(customer.getCustomerName());
        updatedCustomer.setVersion(customer.getVersion());

        customerMap.put(updatedCustomer.getId(),updatedCustomer);

        return updatedCustomer;
    }

    @Override
    public void deleteById(UUID id) {
        this.customerMap.remove(id);
    }

    @Override
    public void patchById(UUID id, Customer customer) {
        Customer updatedCustomer=customerMap.get(id);

        if (StringUtils.hasText(updatedCustomer.getCustomerName())){
            updatedCustomer.setCustomerName((customer.getCustomerName()));
        }
        if (updatedCustomer.getVersion() != null) {
            updatedCustomer.setVersion(customer.getVersion());
        }


    }
}
