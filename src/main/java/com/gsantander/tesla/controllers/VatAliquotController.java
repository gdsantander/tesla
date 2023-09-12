package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslVatAliquot;
import com.gsantander.tesla.services.VatAliquotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/vatAliquots")
public class VatAliquotController {

    private final VatAliquotService vatAliquotService;

    public VatAliquotController(VatAliquotService vatAliquotService) {
        this.vatAliquotService = vatAliquotService;
    }

    @PostMapping
    public void insertVatAliquot(@RequestBody @Valid TslVatAliquot tslVatAliquot) {
        this.vatAliquotService.insertVatAliquot(tslVatAliquot);
    }

    @PutMapping
    public void updateVatAliquot(@RequestBody @Valid TslVatAliquot tslVatAliquot) {
        this.vatAliquotService.updateVatAliquot(tslVatAliquot);
    }

    @DeleteMapping("/{id}")
    public void VatAliquot(@PathVariable Integer id) {
        this.vatAliquotService.deleteVatAliquot(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TslVatAliquot>> getVatAliquots() {
        List<TslVatAliquot> tlsVatAliquots = this.vatAliquotService.getVatAliquots();
        return new ResponseEntity<>(tlsVatAliquots,HttpStatus.OK);
    }

    @GetMapping("/vatAliquot/{id}")
    public ResponseEntity<TslVatAliquot> getVatAliquot(@PathVariable Integer id) {
        TslVatAliquot tslVatAliquot = this.vatAliquotService.getVatAliquot(id);
        return new ResponseEntity<>(tslVatAliquot,HttpStatus.OK);
    }

}
