package com.joaoeduardo.penabrancadelivery_backend.user;

import com.joaoeduardo.penabrancadelivery_backend.config.EmailService;
import com.joaoeduardo.penabrancadelivery_backend.security.TokenService;
import com.joaoeduardo.penabrancadelivery_backend.security.exception.AuthenticationException;
import com.joaoeduardo.penabrancadelivery_backend.user.exception.EmailAlreadyRegisteredException;
import com.joaoeduardo.penabrancadelivery_backend.user.exception.UserAlreadyEnabledException;
import com.joaoeduardo.penabrancadelivery_backend.user.exception.UserNotFoundException;
import com.joaoeduardo.penabrancadelivery_backend.user.exception.UserVerificationException;
import com.joaoeduardo.penabrancadelivery_backend.util.RandomString;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final TokenService tokenService;

    public User registerUser(User user) throws MessagingException, IOException {

        Optional<User> foundUser = Optional.ofNullable((User) userRepository.findByEmail(user.getEmail()));

        if (foundUser.isPresent()){

            throw new EmailAlreadyRegisteredException("The email: "+user.getEmail()+ " is already registered!");

        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.generateRandomString(64);
        user.setVerificationCode(randomCode);

        emailService.sendVerificationEmail(user);

        User savedUser = userRepository.save(user);

        return savedUser;

    }

    public UserResponse getUserByToken(String token) {

        Optional<String> userEmail = Optional.ofNullable(tokenService.getSubject(token));

        if (userEmail.isEmpty()){
            throw new UserNotFoundException("User not found with token: "+token);
        }

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userEmail.get()));

        if (user.isEmpty()){
            throw new UserNotFoundException("User not found with email: "+userEmail.get());
        }

        return new UserResponse(user.get());


    }



    public boolean verify(String verificationCode){
        Optional<User> user = Optional.ofNullable(userRepository.findByVerificationCode(verificationCode));

        if (user.isEmpty()){
            throw new UserVerificationException("The provided verification code is invalid!");
        }

        User rawUser = user.get();

        if (rawUser.isEnabled()){
            throw new UserAlreadyEnabledException("The user "+rawUser.getName()+"is already enabled!");
        }

        rawUser.setVerificationCode("");
        rawUser.setEnabled(true);

        userRepository.save(rawUser);

        return true;

    }

    public UserResponse getUserDetails(UUID userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            throw new UserNotFoundException("User not found with ID: "+userId);
        }

        User rawUser = user.get();

        return new UserResponse(rawUser);

    }


}
