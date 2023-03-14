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
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;


    @RequestMapping( method = RequestMethod.GET)
    public List<Customer> listsCustomer(){
        return this.customerService.listCustomers();
    }

    @RequestMapping(value="/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId")UUID id){
        return this.customerService.getCustomerById(id);
    }
    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer){

        Customer savedCustomer=customerService.saveNewCustomer(customer);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateById(@PathVariable("customerId") UUID id,@RequestBody Customer customer){
        customerService.updateCustomerById(id,customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId")
    public ResponseEntity deleteById(@PathVariable("customerId") UUID id, @RequestBody Customer customer){
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{customerId")
    public ResponseEntity patchById(@PathVariable("customerId") UUID id, @RequestBody Customer customer){
        customerService.patchById(id,customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
