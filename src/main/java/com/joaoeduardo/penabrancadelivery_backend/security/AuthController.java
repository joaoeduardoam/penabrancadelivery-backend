package com.joaoeduardo.penabrancadelivery_backend.security;


import com.joaoeduardo.penabrancadelivery_backend.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final String LOGIN_SUCCESSFULLY_MESSAGE = "Login success!!";

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity effectuateLogin(@RequestBody @Valid AuthRequest authRequest) {

        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new AuthResponse(tokenJWT, LOGIN_SUCCESSFULLY_MESSAGE));


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
