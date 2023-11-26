package com.dadr.socialnetwork.service;

import com.dadr.socialnetwork.dto.LoginDto;
import com.dadr.socialnetwork.dto.RegisterDto;
import com.dadr.socialnetwork.entity.User;
import com.dadr.socialnetwork.exception.ApplicationApiException;
import com.dadr.socialnetwork.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.username(), loginDto.password());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }

    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.username())) {
            throw new ApplicationApiException("User already exists with this username: " + registerDto.username());
        }

        User user = User.builder()
                .firstName(registerDto.firstName())
                .lastName(registerDto.lastName())
                .dateOfBirth(registerDto.dateOfBirth())
                .username(registerDto.username())
                .password(passwordEncoder.encode(registerDto.password()))
                .build();
        userRepository.save(user);

        return "user registered successfully";
    }
}
