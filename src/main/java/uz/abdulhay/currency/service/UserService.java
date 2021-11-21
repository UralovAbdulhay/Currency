package uz.abdulhay.currency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.abdulhay.currency.entity.user.User;
import uz.abdulhay.currency.entity.user.superEntity.Role;
import uz.abdulhay.currency.exceptions.BadRequest;
import uz.abdulhay.currency.exceptions.ResourceNotFound;
import uz.abdulhay.currency.payload.LoginRequest;
import uz.abdulhay.currency.payload.RegRequest;
import uz.abdulhay.currency.payload.Result;
import uz.abdulhay.currency.repository.RoleRepository;
import uz.abdulhay.currency.repository.UserRepository;
import uz.abdulhay.currency.security.JwtTokenProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RoleService  roleService;


    public Result getLogin(LoginRequest request) {

        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> ResourceNotFound.get("User", "login", request.getLogin()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        String token = jwtTokenProvider.createToken(user.getLogin(), user.getRole());


        if (token == null) {
            throw BadRequest.get("ERROR " + HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String, Object> login = new HashMap<>();
        login.put("token", token);
        login.put("username", user.getLogin());
        login.put("success", true);
        return Result.ok(login);
    }

    public Result register(RegRequest regRequest) {

        User user = new User();
        user.setFullName(regRequest.getFullName());
        user.setLogin(regRequest.getLogin());
        user.setPhone(regRequest.getPhone());
        user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
        user.setRole(
                Collections.singletonList(
                        roleService.findByName("ROLE_USER")
                )
        );

        userRepository.save(user);
        return Result.ok("Registered");
    }

}
