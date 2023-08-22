package com.gsantander.tesla.controllers;

import java.util.List;

import com.gsantander.tesla.records.EnumRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsantander.tesla.enums.*;
import com.gsantander.tesla.tools.TslFunctions;

@RestController
@RequestMapping("/api/v1/enums")
public class EnumsController {

    @GetMapping("/userprivileges")
    public ResponseEntity<List<EnumRecord>> getUserPrivileges() {
        return new ResponseEntity<>(TslFunctions.toDTOs(UserPrivilege.class),HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<EnumRecord>> getCountries() {
        return new ResponseEntity<>(TslFunctions.toDTOs(Country.class),HttpStatus.OK);
    }

    @GetMapping("/languages")
    public ResponseEntity<List<EnumRecord>> getLanguages() {
        return new ResponseEntity<>(TslFunctions.toDTOs(Language.class),HttpStatus.OK);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<EnumRecord>> getGenders() {
        return new ResponseEntity<>(TslFunctions.toDTOs(Gender.class),HttpStatus.OK);
    }

    @GetMapping("/personalidtypes")
    public ResponseEntity<List<EnumRecord>> getPersonalIdTypes() {
        return new ResponseEntity<>(TslFunctions.toDTOs(PersonalIdType.class),HttpStatus.OK);
    }

    @GetMapping("/taxidtypes")
    public ResponseEntity<List<EnumRecord>> getTaxIdTypes() {
        return new ResponseEntity<>(TslFunctions.toDTOs(TaxIdType.class),HttpStatus.OK);
    }

    @GetMapping("/vatcategories")
    public ResponseEntity<List<EnumRecord>> getVatCategories() {
        return new ResponseEntity<>(TslFunctions.toDTOs(VatCategory.class),HttpStatus.OK);
    }

}
