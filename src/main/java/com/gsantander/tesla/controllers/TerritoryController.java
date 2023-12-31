package com.gsantander.tesla.controllers;

import java.util.List;

import com.gsantander.tesla.enums.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsantander.tesla.model.TslTerritory;
import com.gsantander.tesla.services.TerritoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/global/territories")
public class TerritoryController {

    private final TerritoryService territoryService;

    public TerritoryController(TerritoryService territoryService) {
        this.territoryService = territoryService;
    }

    @PostMapping
    public void insertTerritory(@RequestBody @Valid TslTerritory tslTerritory) {
        this.territoryService.insertTerritory(tslTerritory);
    }

    @PutMapping
    public void updateTerritory(@RequestBody @Valid TslTerritory tslTerritory) {
        this.territoryService.updateTerritory(tslTerritory);
    }

    @DeleteMapping("/{id}")
    public void Territory(@PathVariable Integer id) {
        this.territoryService.deleteTerritory(id);
    }

    @GetMapping("/provinces/{country}")
    public ResponseEntity<List<TslTerritory>> getProvinces(@PathVariable Country country) {
        List<TslTerritory> tslTerritories = this.territoryService.getProvinces(country);
        return new ResponseEntity<>(tslTerritories,HttpStatus.OK);
    }

    @GetMapping("/territory/{id}")
    public ResponseEntity<TslTerritory> getTerritory(@PathVariable Integer id) {
        TslTerritory tslTerritory = this.territoryService.getTerritory(id);
        return new ResponseEntity<>(tslTerritory,HttpStatus.OK);
    }

}