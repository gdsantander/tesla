package com.gsantander.tesla.controllers;

import java.util.List;

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

import com.gsantander.tesla.model.TslProfile;
import com.gsantander.tesla.services.ProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public void insertProfile(@RequestBody @Valid TslProfile tslProfile) {
        this.profileService.insertProfile(tslProfile);
    }

    @PutMapping
    public void updateProfile(@RequestBody @Valid TslProfile tslProfile) {
        this.profileService.updateProfile(tslProfile);
    }

    @DeleteMapping("/{id}")
    public void Profile(@PathVariable Integer id) {
        this.profileService.deleteProfile(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslProfile>> getProfiles(@PathVariable Integer idCompany) {
        List<TslProfile> tslProfiles = this.profileService.getProfiles(idCompany);
        return new ResponseEntity<>(tslProfiles,HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<TslProfile> getProfile(@PathVariable Integer id) {
        TslProfile tslProfile = this.profileService.getProfile(id);
        return new ResponseEntity<>(tslProfile,HttpStatus.OK);
    }

}