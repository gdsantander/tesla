package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslProductFamily;
import com.gsantander.tesla.services.ProductFamilyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productFamilies")
public class ProductFamilyController {

    private final ProductFamilyService productFamilyService;

    public ProductFamilyController(ProductFamilyService productFamilyService) {
        this.productFamilyService = productFamilyService;
    }

    @PostMapping
    public void insertProductFamily(@RequestBody @Valid TslProductFamily tslProductFamily) {
        this.productFamilyService.insertProductFamily(tslProductFamily);
    }

    @PutMapping
    public void updateProductFamily(@RequestBody @Valid TslProductFamily tslProductFamily) {
        this.productFamilyService.updateProductFamily(tslProductFamily);
    }

    @DeleteMapping("/{id}")
    public void ProductFamily(@PathVariable Integer id) {
        this.productFamilyService.deleteProductFamily(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslProductFamily>> getProductFamilies(@PathVariable Integer idCompany) {
        List<TslProductFamily> tslProductFamilies = this.productFamilyService.getProductFamilys(idCompany);
        return new ResponseEntity<>(tslProductFamilies,HttpStatus.OK);
    }

    @GetMapping("/productFamily/{id}")
    public ResponseEntity<TslProductFamily> getProductFamily(@PathVariable Integer id) {
        TslProductFamily tslProductFamily = this.productFamilyService.getProductFamily(id);
        return new ResponseEntity<>(tslProductFamily,HttpStatus.OK);
    }

}