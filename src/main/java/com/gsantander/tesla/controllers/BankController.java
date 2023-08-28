package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslBank;
import com.gsantander.tesla.services.BankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public void insertBank(@RequestBody @Valid TslBank tslBank) {
        this.bankService.insertBank(tslBank);
    }

    @PutMapping
    public void updateBank(@RequestBody @Valid TslBank tslBank) {
        this.bankService.updateBank(tslBank);
    }

    @DeleteMapping("/{id}")
    public void Bank(@PathVariable Integer id) {
        this.bankService.deleteBank(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TslBank>> getBanks() {
        List<TslBank> tlsBanks = this.bankService.getBanks();
        return new ResponseEntity<>(tlsBanks,HttpStatus.OK);
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<TslBank> getBank(@PathVariable Integer id) {
        TslBank tslBank = this.bankService.getBank(id);
        return new ResponseEntity<>(tslBank,HttpStatus.OK);
    }

}
