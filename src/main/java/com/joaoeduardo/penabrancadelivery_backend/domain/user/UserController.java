package com.joaoeduardo.penabrancadelivery_backend.domain.user;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser (@RequestBody @Valid UserCreateDTO userCreateDTO) throws MessagingException, IOException {

        User savedUser = userService.registerUser(new User(userCreateDTO));

        return ResponseEntity.ok(new UserResponse(savedUser));

    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser (@PathVariable UUID userId){

        UserResponse userResponse = userService.getUserDetails(userId);

        return ResponseEntity.ok(userResponse);


    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserByToken (@RequestHeader("Authorization") String token){

        UserResponse userResponse = userService.getUserByToken(token.substring(7));

        return ResponseEntity.ok(userResponse);


    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser (@Param("code") String code){

        if (userService.verify(code)){
            return ResponseEntity.ok("VERIFY SUCCESS");
        }else{
            return ResponseEntity.ok("VERIFY FAILED!");
        }


    }

    @GetMapping("/test")
    public String test (){

        return "YOU ARE LOGGED IN!";
    }

}
