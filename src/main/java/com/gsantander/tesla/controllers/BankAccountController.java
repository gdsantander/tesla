package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslBankAccount;
import com.gsantander.tesla.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bankAccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public void insertBankAccount(@RequestBody @Valid TslBankAccount tslBankAccount) {
        this.bankAccountService.insertBankAccount(tslBankAccount);
    }

    @PutMapping
    public void updateBankAccount(@RequestBody @Valid TslBankAccount tslBankAccount) {
        this.bankAccountService.updateBankAccount(tslBankAccount);
    }

    @DeleteMapping("/{id}")
    public void BankAccount(@PathVariable Integer id) {
        this.bankAccountService.deleteBankAccount(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslBankAccount>> getBankAccounts(@PathVariable Integer idCompany) {
        List<TslBankAccount> tslBankAccounts = this.bankAccountService.getBankAccounts(idCompany);
        return new ResponseEntity<>(tslBankAccounts,HttpStatus.OK);
    }

    @GetMapping("/bankAccount/{id}")
    public ResponseEntity<TslBankAccount> getBankAccount(@PathVariable Integer id) {
        TslBankAccount tslBankAccount = this.bankAccountService.getBankAccount(id);
        return new ResponseEntity<>(tslBankAccount,HttpStatus.OK);
    }

}