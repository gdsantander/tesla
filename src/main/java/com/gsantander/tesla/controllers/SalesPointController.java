package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslSalesPoint;
import com.gsantander.tesla.services.SalesPointService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salesPoints")
public class SalesPointController {

    private final SalesPointService salesPointService;

    public SalesPointController(SalesPointService salesPointService) {
        this.salesPointService = salesPointService;
    }

    @PostMapping
    public void insertSalesPoint(@RequestBody @Valid TslSalesPoint tslSalesPoint) {
        this.salesPointService.insertSalesPoint(tslSalesPoint);
    }

    @PutMapping
    public void updateSalesPoint(@RequestBody @Valid TslSalesPoint tslSalesPoint) {
        this.salesPointService.updateSalesPoint(tslSalesPoint);
    }

    @DeleteMapping("/{id}")
    public void SalesPoint(@PathVariable Integer id) {
        this.salesPointService.deleteSalesPoint(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslSalesPoint>> getSalesPoints(@PathVariable Integer idCompany) {
        List<TslSalesPoint> tslSalesPoints = this.salesPointService.getSalesPoints(idCompany);
        return new ResponseEntity<>(tslSalesPoints,HttpStatus.OK);
    }

    @GetMapping("/salesPoint/{id}")
    public ResponseEntity<TslSalesPoint> getSalesPoint(@PathVariable Integer id) {
        TslSalesPoint tslSalesPoint = this.salesPointService.getSalesPoint(id);
        return new ResponseEntity<>(tslSalesPoint,HttpStatus.OK);
    }

}