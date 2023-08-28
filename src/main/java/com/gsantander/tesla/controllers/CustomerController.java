package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslCustomer;
import com.gsantander.tesla.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void insertCustomer(@RequestBody @Valid TslCustomer tslCustomer) {
        this.customerService.insertCustomer(tslCustomer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody @Valid TslCustomer tslCustomer) {
        this.customerService.updateCustomer(tslCustomer);
    }

    @DeleteMapping("/{id}")
    public void Customer(@PathVariable Integer id) {
        this.customerService.deleteCustomer(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslCustomer>> getCustomers(@PathVariable Integer idCompany) {
        List<TslCustomer> tslCustomers = this.customerService.getCustomers(idCompany);
        return new ResponseEntity<>(tslCustomers,HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<TslCustomer> getCustomer(@PathVariable Integer id) {
        TslCustomer tslCustomer = this.customerService.getCustomer(id);
        return new ResponseEntity<>(tslCustomer,HttpStatus.OK);
    }

}