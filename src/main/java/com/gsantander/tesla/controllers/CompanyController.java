package com.gsantander.tesla.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsantander.tesla.model.TslCompany;
import com.gsantander.tesla.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/global/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void insertCompany(@RequestBody @Valid TslCompany tslCompany) {
        this.companyService.insertCompany(tslCompany);
    }

    @PutMapping
    public void updateCompany(@RequestBody @Valid TslCompany tslCompany) {
        this.companyService.updateCompany(tslCompany);
    }

    @DeleteMapping("/{id}")
    public void Company(@PathVariable Integer id) {
        this.companyService.deleteCompany(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TslCompany>> getCompanies() {
        List<TslCompany> tslCompanies = this.companyService.getCompanies();
        return new ResponseEntity<>(tslCompanies,HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<TslCompany> getCompany(@PathVariable Integer id) {
        TslCompany tslCompany = this.companyService.getCompany(id);
        return new ResponseEntity<>(tslCompany,HttpStatus.OK);
    }

}
