package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslCBill;
import com.gsantander.tesla.services.CBillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cBills")
public class CBillController {

    private final CBillService cBillService;

    public CBillController(CBillService cBillService) {
        this.cBillService = cBillService;
    }

    @PostMapping
    public void insertCBill(@RequestBody @Valid TslCBill tslCBill) {
        tslCBill = this.cBillService.insertCBill(tslCBill);
        this.cBillService.numbering(tslCBill.getIdCBill());
    }

    @PutMapping
    public void updateCBill(@RequestBody @Valid TslCBill tslCBill) {
        this.cBillService.updateCBill(tslCBill);
    }

    @PutMapping("/annull/{id}")
    public void annullCBill(@PathVariable Integer id) {
        this.cBillService.annullCBill(id);
    }

    @DeleteMapping("/{id}")
    public void CBill(@PathVariable Integer id) {
        this.cBillService.deleteCBill(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslCBill>> getCBills(@PathVariable Integer idCompany) {
        List<TslCBill> tslCBills = this.cBillService.getCBills(idCompany);
        return new ResponseEntity<>(tslCBills,HttpStatus.OK);
    }

    @GetMapping("/cBill/{id}")
    public ResponseEntity<TslCBill> getCBill(@PathVariable Integer id) {
        TslCBill tslCBill = this.cBillService.getCBill(id);
        return new ResponseEntity<>(tslCBill,HttpStatus.OK);
    }

}