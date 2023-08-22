package com.geka.project.ecommercebackend.api.controller.auth;

import com.geka.project.ecommercebackend.api.model.RegistrationBody;

import com.geka.project.ecommercebackend.exception.UserAlreadyExistException;
import com.geka.project.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
