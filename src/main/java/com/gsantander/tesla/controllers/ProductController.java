package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslProduct;
import com.gsantander.tesla.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void insertProduct(@RequestBody @Valid TslProduct tslProduct) {
        this.productService.insertProduct(tslProduct);
    }

    @PutMapping
    public void updateProduct(@RequestBody @Valid TslProduct tslProduct) {
        this.productService.updateProduct(tslProduct);
    }

    @DeleteMapping("/{id}")
    public void Product(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslProduct>> getProducts(@PathVariable Integer idCompany) {
        List<TslProduct> tslProducts = this.productService.getProducts(idCompany);
        return new ResponseEntity<>(tslProducts,HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<TslProduct> getProduct(@PathVariable Integer id) {
        TslProduct tslProduct = this.productService.getProduct(id);
        return new ResponseEntity<>(tslProduct,HttpStatus.OK);
    }

}