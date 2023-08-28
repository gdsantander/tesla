package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslCurrency;
import com.gsantander.tesla.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public void insertCurrency(@RequestBody @Valid TslCurrency tslCurrency) {
        this.currencyService.insertCurrency(tslCurrency);
    }

    @PutMapping
    public void updateCurrency(@RequestBody @Valid TslCurrency tslCurrency) {
        this.currencyService.updateCurrency(tslCurrency);
    }

    @DeleteMapping("/{id}")
    public void Currency(@PathVariable Integer id) {
        this.currencyService.deleteCurrency(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TslCurrency>> getCurrencies() {
        List<TslCurrency> tlsCurrencies = this.currencyService.getCurrencies();
        return new ResponseEntity<>(tlsCurrencies,HttpStatus.OK);
    }

    @GetMapping("/currency/{id}")
    public ResponseEntity<TslCurrency> getCurrency(@PathVariable Integer id) {
        TslCurrency tslCurrency = this.currencyService.getCurrency(id);
        return new ResponseEntity<>(tslCurrency,HttpStatus.OK);
    }

}
