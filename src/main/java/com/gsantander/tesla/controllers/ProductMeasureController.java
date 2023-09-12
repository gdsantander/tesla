package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslProductMeasure;
import com.gsantander.tesla.services.ProductMeasureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productMeasures")
public class ProductMeasureController {

    private final ProductMeasureService productMeasureService;

    public ProductMeasureController(ProductMeasureService productMeasureService) {
        this.productMeasureService = productMeasureService;
    }

    @PostMapping
    public void insertProductMeasure(@RequestBody @Valid TslProductMeasure tslProductMeasure) {
        this.productMeasureService.insertProductMeasure(tslProductMeasure);
    }

    @PutMapping
    public void updateProductMeasure(@RequestBody @Valid TslProductMeasure tslProductMeasure) {
        this.productMeasureService.updateProductMeasure(tslProductMeasure);
    }

    @DeleteMapping("/{id}")
    public void ProductMeasure(@PathVariable Integer id) {
        this.productMeasureService.deleteProductMeasure(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslProductMeasure>> getProductMeasures(@PathVariable Integer idCompany) {
        List<TslProductMeasure> tslProductMeasures = this.productMeasureService.getProductMeasures(idCompany);
        return new ResponseEntity<>(tslProductMeasures,HttpStatus.OK);
    }

    @GetMapping("/productMeasure/{id}")
    public ResponseEntity<TslProductMeasure> getProductMeasure(@PathVariable Integer id) {
        TslProductMeasure tslProductMeasure = this.productMeasureService.getProductMeasure(id);
        return new ResponseEntity<>(tslProductMeasure,HttpStatus.OK);
    }

}