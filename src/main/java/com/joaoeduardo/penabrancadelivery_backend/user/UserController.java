package com.joaoeduardo.penabrancadelivery_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser (@RequestBody UserCreateDTO userCreateDTO){

        User savedUser = userService.registerUser(new User(userCreateDTO));

        return ResponseEntity.ok(savedUser);

    }

}
