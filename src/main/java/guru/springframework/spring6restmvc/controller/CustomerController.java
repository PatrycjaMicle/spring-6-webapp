package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class CustomerController {


    public static final String CUSTOMER_PATH="/api/customers";
    public static final String CUSTOMER_PATH_ID= CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;


    @GetMapping( CUSTOMER_PATH)
    public List<Customer> listsCustomer(){
        return this.customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId")UUID id){
        return this.customerService.getCustomerById(id);
    }
    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody Customer customer){

        Customer savedCustomer=customerService.saveNewCustomer(customer);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId") UUID id,@RequestBody Customer customer){
        customerService.updateCustomerById(id,customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId") UUID id){
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchById(@PathVariable("customerId") UUID id, @RequestBody Customer customer){
        customerService.patchById(id,customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
