package uz.abdulhay.currency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abdulhay.currency.service.CurrencyService;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 36000000)
public class CurrencyController {
    private final CurrencyService currencyService;


//    @PreAuthorize(value = "hasAnyAuthority('USER')")
    @GetMapping("/currencies")
    public ResponseEntity getAll(@RequestParam int page, @RequestParam int size) {
        System.out.println(page);
        return ResponseEntity.ok(currencyService.getAll(page, size));
    }

//    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/currency/{code}")
    public ResponseEntity getByCode(@PathVariable @NotBlank String code) {
        return ResponseEntity.ok(currencyService.getByCode(code));
    }

}
