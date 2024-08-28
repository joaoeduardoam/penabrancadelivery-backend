package com.joaoeduardo.penabrancadelivery_backend.user;

import com.joaoeduardo.penabrancadelivery_backend.user.exception.EmailAlreadyRegisteredException;
import com.joaoeduardo.penabrancadelivery_backend.user.exception.UserNotFoundException;
import com.joaoeduardo.penabrancadelivery_backend.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user){

        Optional<User> foundUser = Optional.ofNullable((User) userRepository.findByEmail(user.getEmail()));

        if (foundUser.isPresent()){

            throw new EmailAlreadyRegisteredException("The email: "+user.getEmail()+ " is already registered!");

        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.generateRandomString(64);
        user.setVerificationCode(randomCode);

        User savedUser = userRepository.save(user);

        return savedUser;

    }

    

}
