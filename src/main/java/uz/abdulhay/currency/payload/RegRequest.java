package uz.abdulhay.currency.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {

    @NotBlank
    String fullName;

    @NotBlank
    String phone;

    @NotBlank
    private String login;

    @NotBlank
    private String password;


}
