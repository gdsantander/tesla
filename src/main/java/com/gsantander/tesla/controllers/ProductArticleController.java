package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslProductArticle;
import com.gsantander.tesla.services.ProductArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productArticles")
public class ProductArticleController {

    private final ProductArticleService productArticleService;

    public ProductArticleController(ProductArticleService productArticleService) {
        this.productArticleService = productArticleService;
    }

    @PostMapping
    public void insertProductArticle(@RequestBody @Valid TslProductArticle tslProductArticle) {
        this.productArticleService.insertProductArticle(tslProductArticle);
    }

    @PutMapping
    public void updateProductArticle(@RequestBody @Valid TslProductArticle tslProductArticle) {
        this.productArticleService.updateProductArticle(tslProductArticle);
    }

    @DeleteMapping("/{id}")
    public void ProductArticle(@PathVariable Integer id) {
        this.productArticleService.deleteProductArticle(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslProductArticle>> getProductArticles(@PathVariable Integer idCompany) {
        List<TslProductArticle> tslProductArticles = this.productArticleService.getProductArticles(idCompany);
        return new ResponseEntity<>(tslProductArticles,HttpStatus.OK);
    }

    @GetMapping("/productArticle/{id}")
    public ResponseEntity<TslProductArticle> getProductArticle(@PathVariable Integer id) {
        TslProductArticle tslProductArticle = this.productArticleService.getProductArticle(id);
        return new ResponseEntity<>(tslProductArticle,HttpStatus.OK);
    }

}