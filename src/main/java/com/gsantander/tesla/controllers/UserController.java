package com.gsantander.tesla.controllers;

import java.util.List;

import com.gsantander.tesla.classes.AuthResponse;
import com.gsantander.tesla.classes.PasswordChangeRequest;
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

import com.gsantander.tesla.model.TslUser;
import com.gsantander.tesla.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void insertUser(@RequestBody @Valid TslUser tlsUser) {
        this.userService.insertUser(tlsUser);
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid TslUser tlsUser) {
        this.userService.updateUser(tlsUser);
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "El usuario es eliminado de la base de datos en forma permanente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @DeleteMapping("/{id}")
    public void User(@PathVariable Integer id) {
        this.userService.deleteUser(id);
    }

    @GetMapping("/list/{idCompany}")
    public ResponseEntity<List<TslUser>> getUsers(@PathVariable Integer idCompany) {
        List<TslUser> tlsUsers = this.userService.getUsers(idCompany);
        return new ResponseEntity<>(tlsUsers,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<TslUser> getUser(@PathVariable Integer id) {
        TslUser tlsUser = this.userService.getUser(id);
        return new ResponseEntity<>(tlsUser,HttpStatus.OK);
    }

    @PutMapping("/passwordchange")
    public void passwordChange(@RequestBody @Valid PasswordChangeRequest request) {
        this.userService.passwordChange(request);
    }

}
