package uz.abdulhay.currency.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abdulhay.currency.payload.LoginRequest;
import uz.abdulhay.currency.payload.RegRequest;
import uz.abdulhay.currency.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 36000000)
public class AuthController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.getLogin(request));
    }


    @PostMapping("/register")
    public ResponseEntity login(@Valid @RequestBody RegRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }


}
